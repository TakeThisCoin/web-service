package ru.tinkoff.edu.java.bot.telegram.commands.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;

@Component
public class UntrackCommand implements Command {
    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "прекратить отслеживание ссылки";
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
