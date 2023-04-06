package ru.tinkoff.edu.java.bot.dto.responses;

import jakarta.validation.constraints.NotNull;

import java.net.URI;

public record LinkResponse(long id, @NotNull URI url) {
}
