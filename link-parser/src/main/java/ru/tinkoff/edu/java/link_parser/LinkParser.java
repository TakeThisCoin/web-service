package ru.tinkoff.edu.java.link_parser;

import ru.tinkoff.edu.java.link_parser.handlers.GitHubHandler;
import ru.tinkoff.edu.java.link_parser.handlers.StackOverflowHandler;

public class LinkParser {
    public ParsersAnswer parse(String url){
        return new GitHubHandler(new StackOverflowHandler(null)).handle(url);
    }
}
