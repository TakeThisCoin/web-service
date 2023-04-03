package ru.tinkoff.edu.java.scrapper.dto.stackexchange.responses;

import java.time.OffsetDateTime;
import java.util.List;

public record StackExchangeQuestionResponse(List<String> tags, boolean is_answered, OffsetDateTime creation_date) {
}