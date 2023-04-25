package ru.tinkoff.edu.java.link_parser;

import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.impl.GitHubParser;
import ru.tinkoff.edu.java.link_parser.handlers.impl.StackOverflowParser;

import java.net.URI;

public class LinkParser {
    public ParserAnswer parse(URI url){
        return new GitHubParser(new StackOverflowParser(null)).handle(url);
    }
}
