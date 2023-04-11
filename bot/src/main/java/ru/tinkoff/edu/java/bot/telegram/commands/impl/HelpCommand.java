package ru.tinkoff.edu.java.bot.telegram.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;

import java.util.List;

@Component
public class HelpCommand implements Command {

    private final List<Command> commands;

    @Autowired
    public HelpCommand(List<Command> commands){
        this.commands = commands;
        commands.add(this);
    }

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

        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : commands) {
            stringBuilder.append(command.command()).append(" - ").append(command.description()).append("\n");
        }

        sendMessage.setText(stringBuilder.toString());

        return sendMessage;
    }
}
