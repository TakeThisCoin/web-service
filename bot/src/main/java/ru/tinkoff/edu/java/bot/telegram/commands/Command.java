package ru.tinkoff.edu.java.bot.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface Command {
    String command();

    String description();

    SendMessage execute(Update update);

    default boolean supports(Message message) {
        return message.getText().equals(command());
    }

    default BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
