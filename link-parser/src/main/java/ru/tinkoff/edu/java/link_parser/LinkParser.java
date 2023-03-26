package ru.tinkoff.edu.java.link_parser;

import ru.tinkoff.edu.java.link_parser.handlers.impl.GitHubHandler;
import ru.tinkoff.edu.java.link_parser.handlers.impl.StackOverflowHandler;

public class LinkParser {
    public ParsersAnswer parse(String url){
        return new GitHubHandler(new StackOverflowHandler(null)).handle(url);
    }
}
