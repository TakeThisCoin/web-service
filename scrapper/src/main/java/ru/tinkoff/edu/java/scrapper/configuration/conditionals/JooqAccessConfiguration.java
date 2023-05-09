package ru.tinkoff.edu.java.scrapper.configuration.conditionals;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.JooqLinkService;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqAccessConfiguration {

    @Bean
    DSLContext dslContext(DataSource dataSource){
        DSLContext context = DSL.using(dataSource, SQLDialect.POSTGRES);
        return context;
    }

    @Bean
    public LinkService linkService(DSLContext dslContext) {
        return new JooqLinkService(dslContext);
    }

    @Bean
    public ChatService chatService(DSLContext dslContext) {
        return new JooqChatService(dslContext);
    }
}
