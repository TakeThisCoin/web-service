package ru.tinkoff.edu.java.scrapper.service.impl.jpa;

import lombok.SneakyThrows;
import migrations.IntegrationEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.entities.Link;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.ChatJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.LinkJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.ChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.LinkRepositoryJpa;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaLinkServiceTest extends IntegrationEnvironment {

    ChatRepositoryJpa chatRepositoryJpa;
    LinkRepositoryJpa linkRepositoryJpa;
    LinkService linkService;
    ChatService chatService;

    @Autowired
    public JpaLinkServiceTest(ChatRepositoryJpa chatRepositoryJpa,
                              LinkRepositoryJpa linkRepositoryJpa){

        linkService = new JpaLinkService(linkRepositoryJpa, chatRepositoryJpa);
        chatService = new JpaChatService(chatRepositoryJpa);

        this.chatRepositoryJpa = chatRepositoryJpa;
        this.linkRepositoryJpa = linkRepositoryJpa;
    }

    @SneakyThrows
    @Test
    void add() {
        String url = "https://JUNIT5.com";
        URI uri = new URI(url);
        long tgChatId = 344325345L;

        ChatJpa chatJpa = new ChatJpa(tgChatId);

        chatRepositoryJpa.save(chatJpa);
        linkService.add(tgChatId, uri);

        LinkJpa linkJpa = linkRepositoryJpa.findByUrl(url).orElse(null);
        chatJpa = chatRepositoryJpa.findById(tgChatId).orElse(null);

        assertNotNull(linkJpa);
        assertNotNull(chatJpa);
        assertTrue(chatJpa.getLinks().contains(linkJpa));
    }

    @SneakyThrows
    @Test
    void remove() {
        String url = "https://JUNIT5.com";
        URI uri = new URI(url);
        long tgChatId = 344325345L;

        LinkJpa linkJpa = new LinkJpa(url);
        linkRepositoryJpa.save(linkJpa);

        ChatJpa chatJpa = new ChatJpa(tgChatId);
        chatJpa.getLinks().add(linkJpa);
        chatRepositoryJpa.save(chatJpa);

        chatJpa = chatRepositoryJpa.findById(tgChatId).orElseThrow();
        assertTrue(chatJpa.getLinks().contains(linkJpa));

        linkService.remove(tgChatId, uri);
        assertFalse(chatJpa.getLinks().contains(linkJpa));
    }

    @SneakyThrows
    @Test
    void listAll() {
        String url = "https://JUNIT5.com1";
        URI uri = new URI(url);

        long tgChatId = 344325345L;

        chatService.register(tgChatId);

        Link link1 = linkService.add(tgChatId, uri);

        List<Link> expected = List.of(
                link1
        );

        assertIterableEquals(expected, linkService.listAll(tgChatId));
    }
}