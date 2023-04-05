package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotEmpty @DefaultValue("https://api.github.com") URI githubApiPath,
                                @NotEmpty @DefaultValue("https://api.stackexchange.com") URI stackExchangeApiPath,
                                @NotEmpty @DefaultValue("2.3") String stackExchangeApiVersion,
                                @NotNull Duration scheduler) {}