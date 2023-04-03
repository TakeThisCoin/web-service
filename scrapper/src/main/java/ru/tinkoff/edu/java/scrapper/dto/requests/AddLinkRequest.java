package ru.tinkoff.edu.java.scrapper.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record AddLinkRequest(@NotNull URI link) {
}
