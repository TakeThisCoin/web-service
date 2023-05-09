package ru.tinkoff.edu.java.scrapper.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.GitHubAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.StackOverflowAnswer;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.github_repository_issue.GitHubRepositoryIssueDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.so_answer_count.AnswerCountSODAO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.GitHubRepositoryIssueDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.AnswerCountSODTO;
import ru.tinkoff.edu.java.scrapper.webclients.github.responses.GitHubRepositoryIssuesResponse;
import ru.tinkoff.edu.java.scrapper.webclients.bot.requests.BotUpdatesRequest;
import ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.responses.StackExchangeQuestionResponse;
import ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.responses.StackExchangeQuestionsResponse;
import ru.tinkoff.edu.java.scrapper.webclients.bot.BotClient;
import ru.tinkoff.edu.java.scrapper.webclients.github.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.StackOverflowClient;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LinkUpdaterScheduler {

    private final Duration checkDelaySOLinks;
    private final Duration checkDelayGitHubLinks;
    private final ChatLinkDAO chatLinkDAO;
    private final LinkDAO linkDAO;
    private final AnswerCountSODAO answerCountSODAO;
    private final GitHubRepositoryIssueDAO gitHubRepositoryIssueDAO;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final BotClient botClient;

    private final LinkParser linkParser = new LinkParser();

    @Autowired
    public LinkUpdaterScheduler(ApplicationConfig applicationConfig,
                                ChatLinkDAO chatLinkDAO,
                                LinkDAO linkDAO,
                                AnswerCountSODAO answerCountSODAO,
                                GitHubRepositoryIssueDAO gitHubRepositoryIssueDAO,
                                GitHubClient gitHubClient,
                                StackOverflowClient stackOverflowClient,
                                BotClient botClient){
        this.checkDelaySOLinks = applicationConfig.checkDelaySOLinks();
        this.checkDelayGitHubLinks = applicationConfig.checkDelayGitHubLinks();
        this.chatLinkDAO = chatLinkDAO;
        this.answerCountSODAO = answerCountSODAO;
        this.linkDAO = linkDAO;
        this.gitHubRepositoryIssueDAO = gitHubRepositoryIssueDAO;
        this.gitHubClient = gitHubClient;
        this.stackOverflowClient = stackOverflowClient;
        this.botClient = botClient;
    }

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMs}")
    public void update() {
        log.info("Scheduler start");

        checkSOLinks(checkDelaySOLinks);
        checkGitHubLinks(checkDelayGitHubLinks);

    }

    private void checkSOLinks(Duration checkDelay){
        List<LinkDTO> allOutdatedLinks = linkDAO.findAllOutdated(
                new Timestamp(checkDelay.getSeconds())
        );

        for (LinkDTO link :
                allOutdatedLinks) {
            ParserAnswer parserAnswer = linkParser.parse(link.url());

            if(parserAnswer instanceof StackOverflowAnswer stackOverflowAnswer)
                checkSOLink(stackOverflowAnswer, link);
        }

    }

    private void checkSOLink(StackOverflowAnswer stackOverflowAnswer, LinkDTO link){
        // проверка на обновления новых ответов
        StackExchangeQuestionsResponse response = stackOverflowClient.fetchQuestions(stackOverflowAnswer.getValue());

        for (StackExchangeQuestionResponse stackExchangeQuestion :
                response.items()) {

            long countNewAnswers = 0;

            if(!answerCountSODAO.containsByLinkId(link.id())){
                AnswerCountSODTO answerCount = new AnswerCountSODTO(link.id(), stackExchangeQuestion.answer_count());
                answerCount = answerCountSODAO.add(answerCount);
                countNewAnswers = answerCount.count();
            }else {
                AnswerCountSODTO answerCount = answerCountSODAO.findByLinkId(link.id());
                if(answerCount.count() < stackExchangeQuestion.answer_count()){
                    countNewAnswers = stackExchangeQuestion.answer_count() - answerCount.count();
                    answerCount = new AnswerCountSODTO(link.id(), stackExchangeQuestion.answer_count());
                    answerCountSODAO.update(answerCount);
                }
            }

            if(countNewAnswers > 0){
                List<Long> tgChatIds = getTgChatIdsByLinkId(link.id());

                BotUpdatesRequest request = new BotUpdatesRequest(link.url(),
                        "\uD83D\uDC4C\uD83C\uDFFBНовые ответы ("+countNewAnswers+") на StackOverflow!\n" +
                                "-"+link.url(), tgChatIds);
                botClient.sendUpdates(request);
            }
        }

        linkDAO.updateLastCheck(link);
    }

    private void checkGitHubLinks(Duration checkDelay){
        List<LinkDTO> allOutdatedLinks = linkDAO.findAllOutdated(
                new Timestamp(checkDelay.getSeconds())
        );

        for (LinkDTO link :
                allOutdatedLinks) {
            ParserAnswer parserAnswer = linkParser.parse(link.url());

            if(parserAnswer instanceof GitHubAnswer gitHubAnswer)
                checkGitHubLink(gitHubAnswer, link);
        }

    }

    private void checkGitHubLink(GitHubAnswer gitHubAnswer, LinkDTO link){
        // проверка на обновления открытых issues
        String state = "open";

        List<GitHubRepositoryIssuesResponse> issues = gitHubClient.getRepositoryIssues(gitHubAnswer.getOwner(), gitHubAnswer.getRepo(), state);

        for (GitHubRepositoryIssuesResponse issue : issues) {

            GitHubRepositoryIssueDTO gitHubRepositoryIssueDTO = new GitHubRepositoryIssueDTO(issue.id(), link.id(), state);

            if(!gitHubRepositoryIssueDAO.contains(gitHubRepositoryIssueDTO)){
                gitHubRepositoryIssueDAO.add(gitHubRepositoryIssueDTO);
                List<Long> tgChatIds = getTgChatIdsByLinkId(link.id());

                BotUpdatesRequest request = new BotUpdatesRequest(link.url(),
                        "✋\uD83C\uDFFBНовая не разрешённая проблема на GitHub!\n" +
                                "-"+issue.html_url(), tgChatIds);
                botClient.sendUpdates(request);
            }
        }

        linkDAO.updateLastCheck(link);
    }

    private List<Long> getTgChatIdsByLinkId(long linkId){
        List<Long> tgChatIds = new ArrayList<>();
        for (ChatLinkDTO chatLink :
                chatLinkDAO.findAllByLink(linkId)) {
            tgChatIds.add(chatLink.chatId());
        }
        return tgChatIds;
    }


}
