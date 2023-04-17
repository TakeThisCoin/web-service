package ru.tinkoff.edu.java.bot.telegram.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.dto.responses.ListLinksResponse;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;
import ru.tinkoff.edu.java.bot.webclients.ScrapperClient;

@Component
public class ListCommand implements Command {

    private final ScrapperClient scrapperClient;

    @Autowired
    public ListCommand(ScrapperClient scrapperClient){
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "показать список отслеживаемых ссылок";
    }

    @Override
    public SendMessage execute(Update update) {
        Message message = update.getMessage();
        long chatId = message.getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode("HTML");
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chatId);

        ListLinksResponse listLinksResponse = scrapperClient.getLinks(chatId);
        String text;
        if(listLinksResponse.size() == 0){
            text = "Список ссылок пуст!";
        }else {
            text = scrapperClient.getLinks(chatId).links().stream().map(Record::toString).reduce("\n", String::concat);
        }

        sendMessage.setText(text);
        return sendMessage;
    }
}
