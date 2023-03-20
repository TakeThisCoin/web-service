package ru.tinkoff.edu.java.link_parser.handlers;

public class GitHubHandler implements Handler{

    public static String handle(String entry){
        if(!entry.contains("/") || entry.split("/").length < 2)
            return null;

        String user = entry.split("/")[0];
        String repo = entry.split("/")[1];

        if(user.length() > 0 && repo.length() > 0){
            return user+"/"+repo;
        }
        return null;
    }

}
