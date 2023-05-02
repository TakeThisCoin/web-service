package ru.tinkoff.edu.java.scrapper.configuration.conditionals;

import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.JooqLinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.ChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.LinkRepositoryJpa;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean
    public LinkService linkService(LinkRepositoryJpa linkRepositoryJpa,
                                   ChatRepositoryJpa chatRepositoryJpa) {
        return new JpaLinkService(linkRepositoryJpa, chatRepositoryJpa);
    }

    @Bean
    public ChatService chatService(ChatRepositoryJpa chatRepositoryJpa) {
        return new JpaChatService(chatRepositoryJpa);
    }
}
