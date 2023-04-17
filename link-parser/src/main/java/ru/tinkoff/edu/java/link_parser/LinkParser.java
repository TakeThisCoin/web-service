package ru.tinkoff.edu.java.link_parser;

import ru.tinkoff.edu.java.link_parser.handlers.impl.GitHubParser;
import ru.tinkoff.edu.java.link_parser.handlers.impl.StackOverflowParser;

public class LinkParser {
    public ParserAnswer parse(String url){
        return new GitHubParser(new StackOverflowParser(null)).handle(url);
    }
}
