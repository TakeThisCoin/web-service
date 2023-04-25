package ru.tinkoff.edu.java.scrapper.domain.dao.link.impl;

import lombok.SneakyThrows;
import migrations.IntegrationEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkDAOImplTest extends IntegrationEnvironment {

    private static JdbcTemplate jdbcTemplate;
    private static LinkDAO linkDAO;

    @BeforeAll
    static void setUp() {
        DataSource dataSource = dataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        linkDAO = new LinkDAOImpl(jdbcTemplate);
    }

    static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgreSQLContainer.getDriverClassName());
        dataSource.setUrl(postgreSQLContainer.getJdbcUrl());
        dataSource.setUsername(postgreSQLContainer.getUsername());
        dataSource.setPassword(postgreSQLContainer.getPassword());
        return dataSource;
    }

    @Test
    @SneakyThrows
    void add() {
        long id = 675;
        LinkDTO newLink = linkDAO.add("https://link1");

        List<LinkDTO> proof = jdbcTemplate.query("SELECT * FROM link WHERE id = ?;", new Object[]{newLink.id()}, new LinkMapper());

        assertEquals(newLink.id(), proof.get(0).id());

        linkDAO.remove(newLink);
    }

    @Test
    public void findByUrl(){
        String link = "https://link1";

        LinkDTO expectedLink = linkDAO.add(link);
        LinkDTO actualLink = linkDAO.findByUrl(link);

        assertEquals(expectedLink, actualLink);

        linkDAO.remove(actualLink);
    }

    @Test
    void remove() {
        LinkDTO newLink = linkDAO.add("https://link1");
        linkDAO.remove(newLink);

        List<LinkDTO> proof = jdbcTemplate.query("SELECT * FROM link WHERE id = ?;", new Object[]{newLink.id()}, new LinkMapper());

        assertEquals(0, proof.size());
    }

    @Test
    void findAll() {
        LinkDTO newLink = linkDAO.add("https://link1");
        LinkDTO newLink1 = linkDAO.add("https://link2");

        List<LinkDTO> checkList = linkDAO.findAll();

        List<LinkDTO> expected = List.of(
                newLink,
                newLink1
        );

        assertIterableEquals(expected, checkList);

        linkDAO.remove(newLink);
        linkDAO.remove(newLink1);
    }

    @SneakyThrows
    @Test
    void checkTimestamp(){
        LinkDTO expectedLink = linkDAO.add("https://link1234");
        LinkDTO actualLink = linkDAO.findByUrl("https://link1234");

        assertEquals(expectedLink.last_update().getTime(), actualLink.last_update().getTime());
    }
}