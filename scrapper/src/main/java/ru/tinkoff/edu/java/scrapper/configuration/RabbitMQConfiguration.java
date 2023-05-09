package ru.tinkoff.edu.java.scrapper.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "bot-client-sender", havingValue = "amqp")
public class RabbitMQConfiguration {
    private final AmqpConfig amqpConfig;

    @Autowired
    public RabbitMQConfiguration(ApplicationConfig config){
        this.amqpConfig = config.amqp();
    }

    @Bean
    public Queue myQueue() {
        return QueueBuilder.durable(amqpConfig.queueName())
                .withArgument("x-dead-letter-exchange", amqpConfig.exchangeName())
                .withArgument("x-dead-letter-routing-key", amqpConfig.routingKey()+".dlq")
                .build();
    }

    @Bean
    public Queue myQueueDLQ() {
        return QueueBuilder.durable(amqpConfig.queueName()+".dlq").build();
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(amqpConfig.exchangeName());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(myQueue()).to(directExchange()).with(amqpConfig.routingKey());
    }

    @Bean
    public Binding bindingDlq() {
        return BindingBuilder.bind(myQueueDLQ()).to(directExchange()).with(amqpConfig.routingKey()+".dlq");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
