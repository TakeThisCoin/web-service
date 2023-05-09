package ru.tinkoff.edu.java.scrapper.service.impl.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.ChatJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.ChatRepositoryJpa;

@RequiredArgsConstructor()
public class JpaChatService implements ChatService {
    private final ChatRepositoryJpa chatRepositoryJpa;

    @Override
    public void register(long tgChatId) {
        ChatJpa chatJpa = new ChatJpa(tgChatId);
        chatRepositoryJpa.save(chatJpa);
    }

    @Override
    public void unregister(long tgChatId) {
        ChatJpa chatJpa = new ChatJpa(tgChatId);
        chatRepositoryJpa.delete(chatJpa);
    }
}
