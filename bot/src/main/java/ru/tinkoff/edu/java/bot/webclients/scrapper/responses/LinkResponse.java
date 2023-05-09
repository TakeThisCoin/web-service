package ru.tinkoff.edu.java.bot.webclients.scrapper.responses;

import jakarta.validation.constraints.NotNull;

import java.net.URI;

public record LinkResponse(long id, @NotNull URI url) {
}
