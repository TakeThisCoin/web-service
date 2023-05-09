package ru.tinkoff.edu.java.link_parser.handlers.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;

import java.net.URI;
import java.time.Instant;

class GitHubParserTest {

    private static GitHubParser gitHubParser;

    @BeforeAll
    public static void setUp(){
        gitHubParser = new GitHubParser(null);
    }

    @SneakyThrows
    @Test
    public void parseValidLink(){
        String url = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        ParserAnswer parserAnswer = gitHubParser.handle(new URI(url));
        Assertions.assertEquals("sanyarnd/tinkoff-java-course-2022", parserAnswer.getValue());
    }

    @SneakyThrows
    @Test
    public void parseInvalidLink_Host(){
        String url = "https://github1.com/sanyarnd/tinkoff-java-course-2022/";
        ParserAnswer parserAnswer = gitHubParser.handle(new URI(url));
        Assertions.assertNull(parserAnswer.getValue());
    }

    @SneakyThrows
    @Test
    public void parseInvalidLink_WithoutUser(){
        String url = "https://github.com//tinkoff-java-course-2022/";
        ParserAnswer parserAnswer = gitHubParser.handle(new URI(url));
        Assertions.assertNull(parserAnswer.getValue());
    }

    @SneakyThrows
    @Test
    public void parseInvalidLinkV2_WithoutUser(){
        String url = "https://github.com/tinkoff-java-course-2022/";
        ParserAnswer parserAnswer = gitHubParser.handle(new URI(url));
        Assertions.assertNull(parserAnswer.getValue());
    }

}