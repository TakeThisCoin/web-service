package ru.tinkoff.edu.java.link_parser.handlers;

import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.UnknownAnswer;

import java.net.URI;

public abstract class Parser {
    Parser nextParser;

    public Parser(Parser nextParser){
        this.nextParser = nextParser;
    }

    public abstract ParserAnswer handle(final URI uri);

    public ParserAnswer toNextHandler(final URI uri){
        if(nextParser != null)
            return nextParser.handle(uri);
        else
            return new UnknownAnswer();
    }
}
