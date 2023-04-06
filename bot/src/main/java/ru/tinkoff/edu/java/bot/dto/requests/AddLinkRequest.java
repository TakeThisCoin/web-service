package ru.tinkoff.edu.java.bot.dto.requests;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record AddLinkRequest(@NotNull URI link) {
}