package ru.tinkoff.edu.java.link_parser.handlers;

public class StackOverflowHandler implements Handler{

    public static String handle(String entry){
        if(!entry.contains("questions/") || entry.split("questions/").length < 2)
            return null;

        String id = entry.split("questions/")[1].split("/")[0];

        if(id.matches("\\d+")){
            return id;
        }else {
            return null;
        }
    }
}
