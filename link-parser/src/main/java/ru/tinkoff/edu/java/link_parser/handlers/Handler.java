package ru.tinkoff.edu.java.link_parser.handlers;

import ru.tinkoff.edu.java.link_parser.ParsersAnswer;

import java.util.Objects;

public abstract class Handler {
    Handler nextHandler;

    public Handler(Handler nextHandler){
        this.nextHandler = nextHandler;
    }

    public abstract ParsersAnswer handle(String entry);

    public ParsersAnswer toNextHandler(final String entry){
        if(nextHandler != null)
            return nextHandler.handle(entry);
        else
            return new ParsersAnswer(null);
    }
}
