package ru.tinkoff.edu.java.link_parser.handlers.impl;

import ru.tinkoff.edu.java.link_parser.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.Parser;

public class StackOverflowParser extends Parser {

    public StackOverflowParser(Parser nextParser) {
        super(nextParser);
    }

    @Override
    public ParserAnswer handle(final String entry) {
        if(!entry.contains("://") || entry.split("://").length != 2)
            return toNextHandler(entry);

        final String entryWithoutProtocol = entry.split("://")[1];

        if(!entryWithoutProtocol.contains("/") || entryWithoutProtocol.split("/").length < 2)
            return toNextHandler(entry);

        final String hostname = entryWithoutProtocol.split("/")[0];
        final String path = entryWithoutProtocol.substring(hostname.length()+1);

        if(!hostname.equals("stackoverflow.com"))
            return toNextHandler(entry);

        if(!path.contains("questions/") || path.split("questions/").length < 2)
            return toNextHandler(entry);

        final String id = path.split("questions/")[1].split("/")[0];

        if(id.matches("\\d+")){
            return new ParserAnswer(id);
        }else {
            return toNextHandler(entry);
        }
    }
}
