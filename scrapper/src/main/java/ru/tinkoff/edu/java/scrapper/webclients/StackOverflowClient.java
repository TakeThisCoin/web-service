package ru.tinkoff.edu.java.scrapper.webclients;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tinkoff.edu.java.scrapper.dto.stackexchange.responses.StackExchangeQuestionsResponse;

public interface StackOverflowClient {
    @GetMapping("/{version}/questions/{ids}?site=stackoverflow")
    StackExchangeQuestionsResponse fetchQuestions(@PathVariable String version, @PathVariable String ids);
}
