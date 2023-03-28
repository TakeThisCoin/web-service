package ru.tinkoff.edu.java.scrapper.dto.github.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubRepositoryResponse(
        long id,
        String name,
        OffsetDateTime created_at,
        @JsonProperty("updated_at") OffsetDateTime updatedAt,
        GitHubRepositoryOwnerResponse owner
) {
}
