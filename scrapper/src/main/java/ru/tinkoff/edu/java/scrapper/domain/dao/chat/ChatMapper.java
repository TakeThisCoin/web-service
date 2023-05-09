package ru.tinkoff.edu.java.scrapper.domain.dao.chat;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatMapper implements RowMapper<ChatDTO> {
    @Override
    public ChatDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChatDTO(rs.getLong("id"));
    }
}
