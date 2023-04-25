package ru.tinkoff.edu.java.scrapper.domain.dao.link.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LinkDAOImpl implements LinkDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public LinkDTO add(String url) {
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

    @Override
    public List<LinkDTO> findAllOutdated(Timestamp timePassed) {
        return jdbcTemplate.query("SELECT * FROM link WHERE (extract(epoch from now())::integer - extract(epoch from last_check)::integer) >= ?;", new LinkMapper(), timePassed.getTime());
    }

    @Override
    public LinkDTO updateLastCheck(LinkDTO linkDTO) {
        jdbcTemplate.update("UPDATE link SET last_check = now() WHERE id = ?", linkDTO.id());
        return findByUrl(linkDTO.url().toString());
    }
}
