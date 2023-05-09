package ru.tinkoff.edu.java.link_parser.handlers.impl;

import lombok.SneakyThrows;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.GitHubAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.Parser;

import java.net.URI;

public class GitHubParser extends Parser {

    public GitHubParser(Parser nextParser) {
        super(nextParser);
    }

    @SneakyThrows
    @Override
    public ParserAnswer handle(final URI uri) {
        String url = uri.toString();

        if(!url.contains("://") || url.split("://").length != 2)
            return toNextHandler(new URI(url));

        final String entryWithoutProtocol = url.split("://")[1];

        if(!entryWithoutProtocol.contains("/") || entryWithoutProtocol.split("/").length < 2)
            return toNextHandler(new URI(url));

        final String hostname = entryWithoutProtocol.split("/")[0];
        final String path = entryWithoutProtocol.substring(hostname.length()+1);

        if(!hostname.equals("github.com"))
            return toNextHandler(new URI(url));

        if(!path.contains("/") || path.split("/").length < 2)
            return toNextHandler(new URI(url));

        final String user = path.split("/")[0];
        final String repo = path.split("/")[1];

        if(user.length() > 0 && repo.length() > 0){
            return new GitHubAnswer(user+"/"+repo);
        }

        return toNextHandler(new URI(url));
    }

}
