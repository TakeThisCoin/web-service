package ru.tinkoff.edu.java.scrapper.rest_api.dto.responses;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record LinkResponse(long id, @NotNull URI url) {
}
