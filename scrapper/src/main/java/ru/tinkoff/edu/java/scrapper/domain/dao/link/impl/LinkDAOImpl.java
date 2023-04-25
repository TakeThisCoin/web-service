package ru.tinkoff.edu.java.scrapper.domain.dao.link.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LinkDAOImpl implements LinkDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public LinkDTO add(String url) {
        // jdbcTemplate.update("INSERT INTO link(url) VALUES (?);", url);
        return jdbcTemplate.queryForObject("INSERT INTO link(url) VALUES (?) RETURNING *;", new LinkMapper(), url);
    }

    @Override
    public LinkDTO findByUrl(String link) {
        return jdbcTemplate.queryForObject("SELECT * FROM link WHERE url = ?", new LinkMapper(), link);
    }

    @Override
    public void remove(LinkDTO linkDTO) {
        jdbcTemplate.update("DELETE FROM link WHERE id = ?", linkDTO.id());
    }

    @Override
    public List<LinkDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM link", new LinkMapper());
    }
}
