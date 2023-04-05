package ru.tinkoff.edu.java.scrapper.exceptions;

import java.util.List;

public class NotExists404Exception extends RuntimeException{
    public NotExists404Exception(String message) {
        super(message);
    }
}
