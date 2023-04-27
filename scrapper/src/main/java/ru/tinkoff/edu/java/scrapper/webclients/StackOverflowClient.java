package ru.tinkoff.edu.java.scrapper.webclients;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import ru.tinkoff.edu.java.scrapper.dto.stackexchange.responses.StackExchangeQuestionsResponse;

public interface StackOverflowClient{
    @GetExchange("/questions/{ids}?site=stackoverflow")
    StackExchangeQuestionsResponse fetchQuestions(@PathVariable String ids);
}
