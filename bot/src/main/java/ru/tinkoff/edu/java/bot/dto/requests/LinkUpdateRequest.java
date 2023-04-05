package ru.tinkoff.edu.java.bot.dto.requests;


import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;

public record LinkUpdateRequest(
        long id,
        @NotNull URI url,
        String description,
        List<Long> tgChatIds
    )
{}
