package ru.tinkoff.edu.java.scrapper.webclients.github.responses;

public record GitHubRepositoryOwnerResponse(
        String login,
        long id) {
}
