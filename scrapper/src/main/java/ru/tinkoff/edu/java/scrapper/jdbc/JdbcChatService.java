package ru.tinkoff.edu.java.scrapper.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatDAO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcChatService implements ChatService {
    private final ChatDAO chatDAO;

    @Override
    public void register(long tgChatId) {
        chatDAO.add(new ChatDTO(tgChatId));
    }

    @Override
    public void unregister(long tgChatId) {
        ChatDTO chatDTO = chatDAO.findById(tgChatId);
        chatDAO.remove(chatDTO);
    }
}
