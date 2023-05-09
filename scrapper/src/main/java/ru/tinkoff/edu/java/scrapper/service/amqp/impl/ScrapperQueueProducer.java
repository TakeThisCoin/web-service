package ru.tinkoff.edu.java.scrapper.service.amqp.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.scrapper.configuration.AmqpConfig;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.service.amqp.BotClientSender;
import ru.tinkoff.edu.java.scrapper.webclients.bot.requests.BotUpdatesRequest;

public class ScrapperQueueProducer implements BotClientSender {
    private final RabbitTemplate rabbitTemplate;
    private final AmqpConfig amqpConfig;

    public ScrapperQueueProducer(ApplicationConfig applicationConfig,
                                 RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.amqpConfig = applicationConfig.amqp();
    }

    @Override
    public void send(BotUpdatesRequest update) {
        rabbitTemplate.convertAndSend(amqpConfig.exchangeName(), amqpConfig.routingKey(), update);
    }
}
