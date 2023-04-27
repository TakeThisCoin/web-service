package ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatLinkDAOImpl implements ChatLinkDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ChatLinkDTO add(ChatLinkDTO chatLink) {
        return jdbcTemplate.queryForObject("INSERT INTO chat_link(chat_id, link_id) VALUES (?, ?) RETURNING *;", new ChatLinkMapper(), chatLink.chatId(), chatLink.linkId());
    }

    @Override
    public void remove(ChatLinkDTO chatLinkDTO) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chat_id = ? AND link_id = ?", chatLinkDTO.chatId(), chatLinkDTO.linkId());
    }

    @Override
    public List<ChatLinkDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat_link", new ChatLinkMapper());
    }

    @Override
    public List<ChatLinkDTO> findAllByLink(long linkId) {
        return jdbcTemplate.query("SELECT * FROM chat_link WHERE link_id = ?", new ChatLinkMapper(), linkId);
    }

}
