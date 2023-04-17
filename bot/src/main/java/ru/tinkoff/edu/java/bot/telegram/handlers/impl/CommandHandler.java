package ru.tinkoff.edu.java.bot.telegram.handlers.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;
import ru.tinkoff.edu.java.bot.telegram.handlers.Handler;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommandHandler implements Handler {

    private final List<Command> commands;

    @Override
    public SendMessage handle(Update update) {
        Message message = update.getMessage();

        return commands.stream()
                .filter(command -> command.supports(message))
                .findFirst()
                .orElseThrow()
                .execute(update);
    }

    @Override
    public boolean supports(Update update) {
        log.info("Проверяем поддержку события в CommandHandler");

        if(!update.hasMessage())
            return false;

        Message message = update.getMessage();
        if(!message.hasText())
            return false;

        return commands.stream()
                .anyMatch(command -> command.supports(message));
    }
}
