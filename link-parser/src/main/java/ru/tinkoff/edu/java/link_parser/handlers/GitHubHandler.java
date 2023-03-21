package ru.tinkoff.edu.java.link_parser.handlers;

import ru.tinkoff.edu.java.link_parser.ParsersAnswer;

public class GitHubHandler extends Handler{

    public GitHubHandler(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public ParsersAnswer handle(final String entry) {
        String entry_l = entry;

        if(!entry_l.contains("://") || entry_l.split("://").length != 2)
            return toNextHandler(entry);

        entry_l = entry_l.split("://")[1];

        if(!entry_l.contains("/") || entry_l.split("/").length < 2)
            return toNextHandler(entry);

        String hostname = entry_l.split("/")[0];
        entry_l = entry_l.substring(hostname.length()+1);

        if(!hostname.equals("github.com"))
            return toNextHandler(entry);

        if(!entry_l.contains("/") || entry_l.split("/").length < 2)
            return toNextHandler(entry_l);

        String user = entry_l.split("/")[0];
        String repo = entry_l.split("/")[1];

        if(user.length() > 0 && repo.length() > 0){
            return new ParsersAnswer(user+"/"+repo);
        }

        return toNextHandler(entry);
    }

}
