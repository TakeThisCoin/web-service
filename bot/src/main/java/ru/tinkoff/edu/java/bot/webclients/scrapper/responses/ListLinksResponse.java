package ru.tinkoff.edu.java.bot.webclients.scrapper.responses;

import java.util.List;

public record ListLinksResponse(List<LinkResponse> links, int size) {
}
