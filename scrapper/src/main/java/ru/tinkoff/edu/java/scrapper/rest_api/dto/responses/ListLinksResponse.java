package ru.tinkoff.edu.java.scrapper.rest_api.dto.responses;

import java.util.List;

public record ListLinksResponse(List<LinkResponse> links, int size) {
}
