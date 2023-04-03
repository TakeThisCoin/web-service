package ru.tinkoff.edu.java.scrapper.dto.responses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.net.URI;

public record LinkResponse(long id, @NotNull URI url) {
}
