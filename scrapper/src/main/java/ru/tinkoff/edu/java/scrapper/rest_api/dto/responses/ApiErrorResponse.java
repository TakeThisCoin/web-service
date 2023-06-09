package ru.tinkoff.edu.java.scrapper.rest_api.dto.responses;

import java.util.List;

public record ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage, List<String> stacktrace) {
}
