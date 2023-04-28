package ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.responses;

import java.util.List;

public record StackExchangeQuestionsResponse(List<StackExchangeQuestionResponse> items) {
}
