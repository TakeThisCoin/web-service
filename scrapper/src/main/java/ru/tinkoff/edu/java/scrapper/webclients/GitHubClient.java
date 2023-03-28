package ru.tinkoff.edu.java.scrapper.webclients;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import ru.tinkoff.edu.java.scrapper.dto.github.responses.GitHubRepositoryResponse;

public interface GitHubClient {
    @GetExchange("/repos/{owner}/{repo}")
    GitHubRepositoryResponse fetchRepository(@PathVariable String owner, @PathVariable String repo);
}
