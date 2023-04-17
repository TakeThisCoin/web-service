package ru.tinkoff.edu.java.bot.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {
    SendMessage handle(Update update);
    boolean supports(Update update);
}
