package ru.tinkoff.edu.java.scrapper.domain.dao.link;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkMapper implements RowMapper<LinkDTO> {

    @SneakyThrows(URISyntaxException.class)
    @Override
    public LinkDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LinkDTO(rs.getLong("id"), new URI(rs.getString("url")));
    }

}
