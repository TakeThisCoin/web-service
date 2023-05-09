package ru.tinkoff.edu.java.link_parser.answers.impl;

import lombok.Getter;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;

public class GitHubAnswer implements ParserAnswer {
    @Getter
    private final String value;
    @Getter
    private final String owner;
    @Getter
    private final String repo;

    public GitHubAnswer(String value){
        this.value = value;
        this.owner = value.split("/")[0];
        this.repo = value.split("/")[1];
    }
}
