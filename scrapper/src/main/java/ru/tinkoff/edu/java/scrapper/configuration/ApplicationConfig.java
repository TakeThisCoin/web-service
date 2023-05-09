package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.configuration.configs.DataBaseConfig;

import java.net.URI;
import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull @DefaultValue("https://api.github.com") URI githubApiBaseUri,
                                @NotNull @DefaultValue("https://api.stackexchange.com") URI stackExchangeApiBaseUri,
                                @NotNull @DefaultValue("http://localhost:8080") URI botBaseUri,
                                @NotEmpty @DefaultValue("2.3") String stackExchangeApiVersion,
                                @NotNull Duration scheduler,
                                @NotNull DataBaseConfig dataBase,
                                @NotNull Duration checkDelaySOLinks,
                                @NotNull Duration checkDelayGitHubLinks) {}