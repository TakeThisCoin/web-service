package ru.tinkoff.edu.java.scrapper.configuration.conditionals;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jdbc.JdbcLinkService;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LinkService linkService(
            LinkDAO linkDAO,
            ChatLinkDAO chatDAO) {
        return new JdbcLinkService(linkDAO, chatDAO);
    }

    @Bean
    public ChatService chatService(
            ChatDAO chatDAO) {
        return new JdbcChatService(chatDAO);
    }

}
