package ru.tinkoff.edu.java.link_parser.handlers;

public class HostHandler implements Handler{

    public static String handle(String entry) {
        if(!entry.contains("://") || entry.split("://").length != 2)
            return null;

        entry = entry.split("://")[1];

        if(!entry.contains("/") || entry.split("/").length < 2)
            return null;

        String hostname = entry.split("/")[0]; //       For current handler
        entry = entry.substring(hostname.length()+1); //      For next handler

        switch (hostname){
            case "github.com":
                return GitHubHandler.handle(entry);
            case "stackoverflow.com":
                return StackOverflowHandler.handle(entry);
            default:
                return null;
        }
    }

}
