package ru.tinkoff.edu.java.link_parser.answers.impl;

import lombok.Getter;
import ru.tinkoff.edu.java.link_parser.answers.ParserAnswer;

public class StackOverflowAnswer implements ParserAnswer {
    @Getter
    private final String value;

    public StackOverflowAnswer(String value){
        this.value = value;
    }
}
