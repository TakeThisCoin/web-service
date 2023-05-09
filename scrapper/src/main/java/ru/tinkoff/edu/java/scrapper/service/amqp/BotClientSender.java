package ru.tinkoff.edu.java.scrapper.service.amqp;

import ru.tinkoff.edu.java.scrapper.webclients.bot.requests.BotUpdatesRequest;

public interface BotClientSender {
    void send(BotUpdatesRequest update);
}
