package ru.tinkoff.edu.java.scrapper.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.GitHubAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.StackOverflowAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.impl.GitHubParser;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;
import ru.tinkoff.edu.java.scrapper.dto.github.responses.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.dto.requests.BotUpdatesRequest;
import ru.tinkoff.edu.java.scrapper.dto.stackexchange.responses.StackExchangeQuestionsResponse;
import ru.tinkoff.edu.java.scrapper.webclients.BotClient;
import ru.tinkoff.edu.java.scrapper.webclients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.StackOverflowClient;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LinkUpdaterScheduler {

    private final Duration checkDelaySOLinks;
    private final Duration checkDelayGitHubLinks;
    private final LinkDAO linkDAO;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final BotClient botClient;

    private final LinkParser linkParser = new LinkParser();

    @Autowired
    public LinkUpdaterScheduler(ApplicationConfig applicationConfig,
                                LinkDAO linkDAO,
                                GitHubClient gitHubClient,
                                StackOverflowClient stackOverflowClient,
                                BotClient botClient){
        this.checkDelaySOLinks = applicationConfig.checkDelaySOLinks();
        this.checkDelayGitHubLinks = applicationConfig.checkDelayGitHubLinks();
        this.linkDAO = linkDAO;
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
                checkSOLink(stackOverflowAnswer);
        }

    }

    private void checkSOLink(StackOverflowAnswer stackOverflowAnswer){
        StackExchangeQuestionsResponse response = stackOverflowClient.fetchQuestions(stackOverflowAnswer.getValue());
        // TODO: проверка на обновления

        BotUpdatesRequest request = new BotUpdatesRequest()
        botClient.sendUpdates()
    }

    private void checkGitHubLinks(Duration checkDelay){
        List<LinkDTO> allOutdatedLinks = linkDAO.findAllOutdated(
                new Timestamp(checkDelay.getSeconds())
        );

        for (LinkDTO link :
                allOutdatedLinks) {
            ParserAnswer parserAnswer = linkParser.parse(link.url());

            if(parserAnswer instanceof GitHubAnswer gitHubAnswer)
                checkGitHubLink(gitHubAnswer);
        }

    }

    private void checkGitHubLink(GitHubAnswer gitHubAnswer){
        GitHubRepositoryResponse response = gitHubClient.fetchRepository(gitHubAnswer.getOwner(), gitHubAnswer.getRepo());
        // TODO: проверка на обновления

        BotUpdatesRequest request = new BotUpdatesRequest()
        botClient.sendUpdates()
    }
}
