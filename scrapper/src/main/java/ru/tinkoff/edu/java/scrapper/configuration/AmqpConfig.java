package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotEmpty;

public record AmqpConfig(
        @NotEmpty String queueName,
        @NotEmpty String routingKey,
        @NotEmpty String exchangeName
) {
}
