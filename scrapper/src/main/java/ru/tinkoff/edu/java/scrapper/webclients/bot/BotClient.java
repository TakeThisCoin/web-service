package ru.tinkoff.edu.java.scrapper.webclients.bot;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.scrapper.webclients.bot.requests.BotUpdatesRequest;
import ru.tinkoff.edu.java.scrapper.webclients.bot.responses.BotUpdatesResponse;

public interface BotClient {
    @PostExchange("/updates")
    BotUpdatesResponse sendUpdates(@RequestBody BotUpdatesRequest body);
}