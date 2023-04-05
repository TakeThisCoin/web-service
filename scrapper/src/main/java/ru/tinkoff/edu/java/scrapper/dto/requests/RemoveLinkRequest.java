package ru.tinkoff.edu.java.scrapper.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.net.URI;

public record RemoveLinkRequest(@NotNull URI link) {
}
