package ru.tinkoff.edu.java.scrapper.dto.stackexchange.responses;

import java.util.List;

public record StackExchangeQuestionsResponse(List<StackExchangeQuestionResponse> items) {
}
