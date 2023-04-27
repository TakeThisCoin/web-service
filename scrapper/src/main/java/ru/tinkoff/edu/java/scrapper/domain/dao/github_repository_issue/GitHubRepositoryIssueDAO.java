package ru.tinkoff.edu.java.scrapper.domain.dao.github_repository_issue;

import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.GitHubRepositoryIssueDTO;

import java.util.List;

public interface GitHubRepositoryIssueDAO {
    GitHubRepositoryIssueDTO add(GitHubRepositoryIssueDTO repositoryIssue);
    boolean contains(GitHubRepositoryIssueDTO repositoryIssue);
}
