package ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.responses;

import java.time.OffsetDateTime;
import java.util.List;

public record StackExchangeQuestionResponse(List<String> tags,
                                            boolean is_answered,
                                            long answer_count,
                                            OffsetDateTime creation_date) {
}
