package ru.tinkoff.edu.java.scrapper.exceptions;

import java.util.List;

public class NotExists404Exception extends RuntimeException{
    private final String description;
    private final String code = "404";
    private final String exceptionName;
    private final String exceptionMessage;
    private final List<String> stacktrace;

    public NotExists404Exception(String description, String exceptionName, String exceptionMessage, List<String> stacktrace) {
        this.description = description;
        this.exceptionName = exceptionName;
        this.exceptionMessage = exceptionMessage;
        this.stacktrace = stacktrace;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public List<String> getStacktrace() {
        return stacktrace;
    }
}
