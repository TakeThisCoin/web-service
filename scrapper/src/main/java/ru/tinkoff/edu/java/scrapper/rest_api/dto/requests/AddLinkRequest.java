package ru.tinkoff.edu.java.scrapper.rest_api.dto.requests;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record AddLinkRequest(@NotNull URI link) {
}
