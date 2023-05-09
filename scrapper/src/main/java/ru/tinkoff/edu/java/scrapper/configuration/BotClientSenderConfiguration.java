package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.service.amqp.BotClientSender;
import ru.tinkoff.edu.java.scrapper.service.amqp.impl.HttpSender;
import ru.tinkoff.edu.java.scrapper.service.amqp.impl.ScrapperQueueProducer;
import ru.tinkoff.edu.java.scrapper.webclients.bot.BotClient;

@Configuration
public class BotClientSenderConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "bot-client-sender", havingValue = "amqp")
    public BotClientSender scrapperQueueProducer(ApplicationConfig config, RabbitTemplate rabbitTemplate){
        return new ScrapperQueueProducer(config, rabbitTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "bot-client-sender", havingValue = "http")
    public BotClientSender httpSender(BotClient botClient){
        return new HttpSender(botClient);
    }
}
