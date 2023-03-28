package ru.tinkoff.edu.java.scrapper.dto.github.responses;

public record GitHubRepositoryOwnerResponse(
        String login,
        long id) {
}
