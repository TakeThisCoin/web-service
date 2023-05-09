package ru.tinkoff.edu.java.scrapper.domain.dao.github_repository_issue.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.github_repository_issue.GitHubRepositoryIssueDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.github_repository_issue.GitHubRepositoryIssueMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.GitHubRepositoryIssueDTO;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GitHubRepositoryIssueDAOImpl  implements GitHubRepositoryIssueDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public GitHubRepositoryIssueDTO add(GitHubRepositoryIssueDTO repositoryIssue) {
        return jdbcTemplate.queryForObject("INSERT INTO github_repository_issue(id, link_id, state) VALUES (?, ?, ?) RETURNING *;", new GitHubRepositoryIssueMapper(), repositoryIssue.id(), repositoryIssue.linkId(), repositoryIssue.state());
    }

    @Override
    public boolean contains(GitHubRepositoryIssueDTO repositoryIssue) {
        String sql = "SELECT count(*) FROM github_repository_issue WHERE id = ? AND link_id = ?";
        boolean exists = false;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, repositoryIssue.id(), repositoryIssue.linkId());
        exists = count != null && count > 0;
        return exists;
    }
}
