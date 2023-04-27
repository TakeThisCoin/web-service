package ru.tinkoff.edu.java.scrapper.webclients;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import ru.tinkoff.edu.java.scrapper.dto.github.responses.GitHubRepositoryIssuesResponse;
import ru.tinkoff.edu.java.scrapper.dto.github.responses.GitHubRepositoryResponse;

import java.util.List;

public interface GitHubClient{
    @GetExchange("/repos/{owner}/{repo}")
    GitHubRepositoryResponse fetchRepository(@PathVariable String owner, @PathVariable String repo);

    @GetExchange("/repos/{owner}/{repo}/issues?state={state}")
    List<GitHubRepositoryIssuesResponse> getRepositoryIssues(@PathVariable String owner, @PathVariable String repo, @PathVariable String state);
}
