package ru.tinkoff.edu.java.scrapper.service.amqp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.scrapper.service.amqp.BotClientSender;
import ru.tinkoff.edu.java.scrapper.webclients.bot.BotClient;
import ru.tinkoff.edu.java.scrapper.webclients.bot.requests.BotUpdatesRequest;

public class HttpSender implements BotClientSender {
    private final BotClient botClient;

    @Autowired
    public HttpSender(BotClient botClient){
        this.botClient = botClient;
    }

    @Override
    public void send(BotUpdatesRequest update) {
        botClient.sendUpdates(update);
    }
}
