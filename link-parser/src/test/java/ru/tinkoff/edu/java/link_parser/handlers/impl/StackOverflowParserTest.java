package ru.tinkoff.edu.java.link_parser.handlers.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;

import java.net.URI;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class StackOverflowParserTest {

    private static StackOverflowParser stackOverflowParser;

    @BeforeAll
    public static void setUp(){
        stackOverflowParser = new StackOverflowParser(null);
    }

    @SneakyThrows
    @Test
    public void parseValidLink(){
        String url = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
        ParserAnswer parserAnswer = stackOverflowParser.handle(new URI(url));
        Assertions.assertEquals("1642028", parserAnswer.getValue());
    }

    @SneakyThrows
    @Test
    public void parseInvalidLink_Host(){
        String url = "https://stackoSverflow.com/questions/1642028/what-is-the-operator-in-c";
        ParserAnswer parserAnswer = stackOverflowParser.handle(new URI(url));
        Assertions.assertNull(parserAnswer.getValue());
    }

    @SneakyThrows
    @Test
    public void parseInvalidLink_IncorrectPath(){
        String url = "https://stackoverflow.com/search?q=unsupported%20link";
        ParserAnswer parserAnswer = stackOverflowParser.handle(new URI(url));
        Assertions.assertNull(parserAnswer.getValue());
    }

}