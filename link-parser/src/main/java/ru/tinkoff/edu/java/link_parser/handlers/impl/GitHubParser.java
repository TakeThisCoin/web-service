package ru.tinkoff.edu.java.link_parser.handlers.impl;

import ru.tinkoff.edu.java.link_parser.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.Parser;

public class GitHubParser extends Parser {

    public GitHubParser(Parser nextParser) {
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

        if(!hostname.equals("github.com"))
            return toNextHandler(entry);

        if(!path.contains("/") || path.split("/").length < 2)
            return toNextHandler(entry);

        final String user = path.split("/")[0];
        final String repo = path.split("/")[1];

        if(user.length() > 0 && repo.length() > 0){
            return new ParserAnswer(user+"/"+repo);
        }

        return toNextHandler(entry);
    }

}
