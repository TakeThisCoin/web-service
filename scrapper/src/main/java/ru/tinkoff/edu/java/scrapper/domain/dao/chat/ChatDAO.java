package ru.tinkoff.edu.java.scrapper.domain.dao.chat;

import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;

import java.util.List;

public interface ChatDAO {
    ChatDTO add(ChatDTO chatDTO);
    ChatDTO findById(long id);
    void remove(ChatDTO chatDTO);

    List<ChatDTO> findAll();
}
