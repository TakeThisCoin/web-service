package ru.tinkoff.edu.java.scrapper.service.impl.jpa;

import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.ChatJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.ChatRepositoryJpa;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaChatServiceTest{

    ChatRepositoryJpa chatRepositoryJpa;
    ChatService chatService;

    @Autowired
    public JpaChatServiceTest(ChatRepositoryJpa chatRepositoryJpa){
        chatService = new JpaChatService(chatRepositoryJpa);
        this.chatRepositoryJpa = chatRepositoryJpa;
    }

    @SneakyThrows
    @Test
    void register() {
        long tgChatId = 3252345235L;
        chatService.register(tgChatId);

        ChatJpa find = chatRepositoryJpa.findById(tgChatId).orElse(null);
        assertNotNull(find);
    }

    @SneakyThrows
    @Test
    void unregister() {
        long tgChatId = 3252345235L;
        chatService.register(tgChatId);
        chatService.unregister(tgChatId);
        ChatJpa find = chatRepositoryJpa.findById(tgChatId).orElse(null);

        assertNull(find);
    }
}