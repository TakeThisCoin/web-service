package ru.tinkoff.edu.java.scrapper.dto.requests;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.List;

@Validated
public record BotUpdatesRequest(@NotNull URI url, String description, List<Long> tgChatIds) {
}
