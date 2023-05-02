package ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.impl;

import lombok.SneakyThrows;
import migrations.IntegrationEnvironment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.ChatDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat.impl.ChatDAOImpl;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.impl.LinkDAOImpl;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GitHubRepositoryIssueDAOImplTest extends IntegrationEnvironment {
    private static JdbcTemplate jdbcTemplate;
    private static ChatLinkDAO chatLinkDAO;
    private static ChatDAO chatDAO;
    private static LinkDAO linkDAO;

    private static ChatDTO chatDTO;
    private static LinkDTO linkDTO;

    @BeforeAll
    static void setUp() {
        DataSource dataSource = dataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        chatLinkDAO = new ChatLinkDAOImpl(jdbcTemplate);
        chatDAO = new ChatDAOImpl(jdbcTemplate);
        linkDAO = new LinkDAOImpl(jdbcTemplate);

        createChat();
        createLink();
    }

    static void createChat(){
        long chatId = 45675675;
        chatDTO = chatDAO.add(new ChatDTO(chatId));
    }

    static void createLink(){
        String linkUrl = "http://retret";
        linkDTO = linkDAO.add(linkUrl);
    }

    static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgreSQLContainer.getDriverClassName());
        dataSource.setUrl(postgreSQLContainer.getJdbcUrl());
        dataSource.setUsername(postgreSQLContainer.getUsername());
        dataSource.setPassword(postgreSQLContainer.getPassword());
        return dataSource;
    }

    @SneakyThrows
    @Test
    void add() {
        ChatLinkDTO newChatLink = new ChatLinkDTO(chatDTO.id(), linkDTO.id());
        chatLinkDAO.add(newChatLink);

        List<ChatLinkDTO> proof = jdbcTemplate.query("SELECT * FROM chat_link WHERE chat_id = ? AND link_id = ?;", new Object[]{newChatLink.chatId(), newChatLink.linkId()}, new ChatLinkMapper());

        assertEquals(newChatLink.chatId(), proof.get(0).chatId());
        assertEquals(newChatLink.linkId(), proof.get(0).linkId());

        chatLinkDAO.remove(newChatLink);
    }

    @SneakyThrows
    @Test
    void findAll() {
        ChatLinkDTO newChatLink = new ChatLinkDTO(chatDTO.id(), linkDTO.id());
        newChatLink = chatLinkDAO.add(newChatLink);

        List<ChatLinkDTO> checkList = chatLinkDAO.findAll();

        List<ChatLinkDTO> expected = List.of(
                newChatLink
        );

        assertIterableEquals(expected, checkList);
        chatLinkDAO.remove(newChatLink);
    }

    @SneakyThrows
    @Test
    void remove() {
        ChatLinkDTO newChatLink = new ChatLinkDTO(chatDTO.id(), linkDTO.id());
        newChatLink = chatLinkDAO.add(newChatLink);

        chatLinkDAO.remove(newChatLink);

        List<ChatLinkDTO> proof = jdbcTemplate.query("SELECT * FROM chat_link WHERE chat_id = ? AND link_id = ?;", new Object[]{newChatLink.chatId(), newChatLink.linkId()}, new ChatLinkMapper());

        assertEquals(0, proof.size());
    }

    @AfterAll
    static void clearDB(){
        linkDAO.remove(linkDTO);
    }
}