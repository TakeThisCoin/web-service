package ru.tinkoff.edu.java.scrapper.webclients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.scrapper.dto.requests.BotUpdatesRequest;
import ru.tinkoff.edu.java.scrapper.dto.responses.BotUpdatesResponse;

public interface BotClient {
    @PostExchange("/updates")
    BotUpdatesResponse sendUpdates(@RequestBody BotUpdatesRequest body);
}