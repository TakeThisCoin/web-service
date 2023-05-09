package ru.tinkoff.edu.java.scrapper.domain.dao.chat_link;

import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;

import java.util.List;

public interface ChatLinkDAO {
    ChatLinkDTO add(ChatLinkDTO chatLink);
    void remove(ChatLinkDTO chatLink);
    List<ChatLinkDTO> findAll();
    List<ChatLinkDTO> findAllByLink(long linkId);
}
