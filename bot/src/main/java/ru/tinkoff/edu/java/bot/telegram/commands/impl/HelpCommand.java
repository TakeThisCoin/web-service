package ru.tinkoff.edu.java.bot.telegram.commands.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;

@Component
public class HelpCommand implements Command {
    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "вывести окно с командами";
    }

    @Override
    public SendMessage execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode("HTML");
        sendMessage.enableHtml(true);
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(description());
        return sendMessage;
    }
}
