package ru.tinkoff.edu.java.link_parser.handlers.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.link_parser.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.Parser;

class GitHubParserTest {

    private static GitHubParser gitHubParser;

    @BeforeAll
    public static void setUp(){
        gitHubParser = new GitHubParser(null);
    }

    @Test
    public void parseValidLink(){
        ParserAnswer parserAnswer = gitHubParser.handle("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        Assertions.assertEquals("sanyarnd/tinkoff-java-course-2022", parserAnswer.value());
    }

    @Test
    public void parseInvalidLink_Host(){
        ParserAnswer parserAnswer = gitHubParser.handle("https://github1.com/sanyarnd/tinkoff-java-course-2022/");
        Assertions.assertNull(parserAnswer.value());
    }

    @Test
    public void parseInvalidLink_WithoutUser(){
        ParserAnswer parserAnswer = gitHubParser.handle("https://github.com//tinkoff-java-course-2022/");
        Assertions.assertNull(parserAnswer.value());
    }

    @Test
    public void parseInvalidLinkV2_WithoutUser(){
        ParserAnswer parserAnswer = gitHubParser.handle("https://github.com/tinkoff-java-course-2022/");
        Assertions.assertNull(parserAnswer.value());
    }

}