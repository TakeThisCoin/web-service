package ru.tinkoff.edu.java.link_parser.handlers;

import ru.tinkoff.edu.java.link_parser.ParsersAnswer;

public class StackOverflowHandler extends Handler{

    public StackOverflowHandler(Handler nextHandler) {
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


        if(!hostname.equals("stackoverflow.com"))
            return toNextHandler(entry);

        if(!entry_l.contains("questions/") || entry_l.split("questions/").length < 2)
            return toNextHandler(entry);

        String id = entry_l.split("questions/")[1].split("/")[0];

        if(id.matches("\\d+")){
            return new ParsersAnswer(id);
        }else {
            return toNextHandler(entry);
        }
    }
}
