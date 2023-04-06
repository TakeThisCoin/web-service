package ru.tinkoff.edu.java.link_parser.handlers;

import ru.tinkoff.edu.java.link_parser.ParserAnswer;

public abstract class Parser {
    Parser nextParser;

    public Parser(Parser nextParser){
        this.nextParser = nextParser;
    }

    public abstract ParserAnswer handle(String entry);

    public ParserAnswer toNextHandler(final String entry){
        if(nextParser != null)
            return nextParser.handle(entry);
        else
            return new ParserAnswer(null);
    }
}
