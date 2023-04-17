package ru.tinkoff.edu.java.link_parser.handlers.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.ParserAnswer;

import static org.junit.jupiter.api.Assertions.*;

class StackOverflowParserTest {

    private static StackOverflowParser stackOverflowParser;

    @BeforeAll
    public static void setUp(){
        stackOverflowParser = new StackOverflowParser(null);
    }

    @Test
    public void parseValidLink(){
        ParserAnswer parserAnswer = stackOverflowParser.handle("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c");
        Assertions.assertEquals("1642028", parserAnswer.value());
    }

    @Test
    public void parseInvalidLink_Host(){
        ParserAnswer parserAnswer = stackOverflowParser.handle("https://stackoSverflow.com/questions/1642028/what-is-the-operator-in-c");
        Assertions.assertNull(parserAnswer.value());
    }

    @Test
    public void parseInvalidLink_IncorrectPath(){
        ParserAnswer parserAnswer = stackOverflowParser.handle("https://stackoverflow.com/search?q=unsupported%20link");
        Assertions.assertNull(parserAnswer.value());
    }

}