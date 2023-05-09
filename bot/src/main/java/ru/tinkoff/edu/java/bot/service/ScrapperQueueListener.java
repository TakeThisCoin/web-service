package ru.tinkoff.edu.java.bot.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.rest_api.dto.requests.LinkUpdateRequest;

@Component
@RabbitListener(queues = "${app.scrapper-queue-name}")
public class ScrapperQueueListener {

    @RabbitHandler
    public void receiver(LinkUpdateRequest update) {
        System.out.println("update: " + update);
    }
}
