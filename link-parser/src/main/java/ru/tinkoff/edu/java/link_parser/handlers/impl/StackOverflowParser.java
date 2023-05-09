package ru.tinkoff.edu.java.link_parser.handlers.impl;

import lombok.SneakyThrows;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;
import ru.tinkoff.edu.java.link_parser.answers.impl.StackOverflowAnswer;
import ru.tinkoff.edu.java.link_parser.handlers.Parser;

import java.net.URI;

public class StackOverflowParser extends Parser {

    public StackOverflowParser(Parser nextParser) {
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

        if(!hostname.equals("stackoverflow.com"))
            return toNextHandler(new URI(url));

        if(!path.contains("questions/") || path.split("questions/").length < 2)
            return toNextHandler(new URI(url));

        final String id = path.split("questions/")[1].split("/")[0];

        if(id.matches("\\d+")){
            return new StackOverflowAnswer(id);
        }else {
            return toNextHandler(new URI(url));
        }
    }
}
