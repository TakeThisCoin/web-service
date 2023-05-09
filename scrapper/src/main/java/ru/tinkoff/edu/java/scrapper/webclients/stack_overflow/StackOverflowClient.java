package ru.tinkoff.edu.java.scrapper.webclients.stack_overflow;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import ru.tinkoff.edu.java.scrapper.webclients.stack_overflow.responses.StackExchangeQuestionsResponse;

public interface StackOverflowClient{
    @GetExchange("/questions/{ids}?site=stackoverflow")
    StackExchangeQuestionsResponse fetchQuestions(@PathVariable String ids);
}
