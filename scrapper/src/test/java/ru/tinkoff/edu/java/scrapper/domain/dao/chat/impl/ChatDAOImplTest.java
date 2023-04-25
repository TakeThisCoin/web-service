package ru.tinkoff.edu.java.scrapper.domain.dao.chat.impl;

import lombok.SneakyThrows;
import migrations.IntegrationEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatDAOImplTest extends IntegrationEnvironment {

    private static JdbcTemplate jdbcTemplate;
    private static ChatDAO chatDAO;

    @BeforeAll
    static void setUp() {
        DataSource dataSource = dataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        chatDAO = new ChatDAOImpl(jdbcTemplate);
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
        ChatDTO newChat = new ChatDTO(id);
        chatDAO.add(newChat);

        List<ChatDTO> proof = jdbcTemplate.query("SELECT * FROM chat WHERE id = ?;", new Object[]{id}, new ChatMapper());

        assertEquals(id, proof.get(0).id());

        chatDAO.remove(newChat);
    }

    @Test
    public void findById(){
        long id = 3223545;

        ChatDTO expectedChat = chatDAO.add(new ChatDTO(id));

        ChatDTO actualChat = chatDAO.findById(id);

        assertEquals(expectedChat, actualChat);

        chatDAO.remove(expectedChat);
    }

    @Test
    void remove() {
        long id = 675;
        ChatDTO newChat = new ChatDTO(id);
        chatDAO.add(newChat);
        chatDAO.remove(newChat);

        List<ChatDTO> proof = jdbcTemplate.query("SELECT * FROM chat WHERE id = ?;", new Object[]{id}, new ChatMapper());

        assertEquals(0, proof.size());
    }

    @Test
    void findAll() {
        long id = 675;
        long id1 = 676;
        ChatDTO newChat = new ChatDTO(id);
        ChatDTO newChat1 = new ChatDTO(id1);
        chatDAO.add(newChat);
        chatDAO.add(newChat1);

        List<ChatDTO> checkList = chatDAO.findAll();

        List<ChatDTO> expected = List.of(
                newChat,
                newChat1
        );

        assertIterableEquals(expected, checkList);

        chatDAO.remove(newChat);
        chatDAO.remove(newChat1);
    }
}