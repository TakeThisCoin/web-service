package ru.tinkoff.edu.java.scrapper.domain.dao.chat.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatDAOImpl implements ChatDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ChatDTO add(ChatDTO chatDTO) {
        return jdbcTemplate.queryForObject("INSERT INTO chat(id) VALUES (?) RETURNING *;", new ChatMapper(), chatDTO.id());
    }

    @Override
    public ChatDTO findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM chat WHERE id = ?", new ChatMapper(), id);
    }

    @Override
    public void remove(ChatDTO chatDTO) {
        jdbcTemplate.update("DELETE FROM chat WHERE id = ?", chatDTO.id());
    }

    @Override
    public List<ChatDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat", new ChatMapper());
    }
}
