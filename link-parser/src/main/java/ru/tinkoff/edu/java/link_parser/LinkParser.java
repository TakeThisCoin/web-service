package ru.tinkoff.edu.java.link_parser;

import ru.tinkoff.edu.java.link_parser.handlers.StartHandler;

public class LinkParser {
    public static String parse(String url){
        return StartHandler.handle(url);
    }
}
