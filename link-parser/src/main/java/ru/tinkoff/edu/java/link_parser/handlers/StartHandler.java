package ru.tinkoff.edu.java.link_parser.handlers;

public class StartHandler implements Handler{

    public static String handle(String entry){
        if(entry != null)
            return HostHandler.handle(entry);
        return null;
    }
}
