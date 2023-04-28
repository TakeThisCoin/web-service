package ru.tinkoff.edu.java.bot.rest_api.dto.requests;


import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;

public record LinkUpdateRequest(
        @NotNull URI url,
        String description,
        List<Long> tgChatIds
    )
{}
