package ru.tinkoff.edu.java.bot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.configuration.TelegramBotConfig;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;
import ru.tinkoff.edu.java.bot.telegram.handlers.Handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;

    private final List<Handler> handlers;

    @Autowired
    public MyTelegramBot(ApplicationConfig applicationConfig, List<Handler> handlers, List<Command> commands){
        super(applicationConfig.telegramBot().token());

        this.telegramBotConfig = applicationConfig.telegramBot();

        this.handlers = Objects.requireNonNullElseGet(handlers, ArrayList::new);

        if(commands != null){
            List<BotCommand> botCommands = commands.stream().map(Command::toApiCommand).collect(Collectors.toList());
            try {
                this.execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("Telegram bot had been started!");
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = getSendMessage(update);
        sendMessage(message);
    }

    public SendMessage getSendMessage(Update update){
        Handler handler = getHandler(update);

        SendMessage sendMessage;
        if(handler == null){
            sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Обработчика для данного события не написано, попробуйте ещё раз!");
        }else {
            sendMessage = handler.handle(update);
        }

        return sendMessage;
    }

    private Handler getHandler(Update update){
        if(update == null)
            return null;

        return handlers.stream()
                .filter(handler -> handler.supports(update))
                .findFirst().orElse(null);
    }

    private void sendMessage(SendMessage sendMessage){
        try {
            log.info("Отправляем сообщение: {}", sendMessage.getText());
            Message result = execute(sendMessage);
            log.info("Сообщение успешно отправлено: {}", result.getText());
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }


    @Override
    public String getBotUsername() {
        return telegramBotConfig.login();
    }
}
