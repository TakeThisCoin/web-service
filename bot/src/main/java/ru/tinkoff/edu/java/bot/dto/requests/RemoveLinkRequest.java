package ru.tinkoff.edu.java.bot.dto.requests;

import jakarta.validation.constraints.NotNull;

import java.net.URI;

public record RemoveLinkRequest(@NotNull URI link) {
}
