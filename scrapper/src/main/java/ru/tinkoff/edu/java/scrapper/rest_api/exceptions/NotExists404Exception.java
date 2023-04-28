package ru.tinkoff.edu.java.scrapper.rest_api.exceptions;

public class NotExists404Exception extends RuntimeException{
    public NotExists404Exception(String message) {
        super(message);
    }
}
