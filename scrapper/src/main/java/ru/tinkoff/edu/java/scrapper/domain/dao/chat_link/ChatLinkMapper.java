package ru.tinkoff.edu.java.scrapper.domain.dao.chat_link;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatLinkMapper implements RowMapper<ChatLinkDTO> {

    @Override
    public ChatLinkDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChatLinkDTO(rs.getLong("chat_id"), rs.getLong("link_id"));
    }
}
