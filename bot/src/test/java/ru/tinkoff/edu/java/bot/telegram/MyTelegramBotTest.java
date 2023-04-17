package ru.tinkoff.edu.java.bot.telegram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.configuration.TelegramBotConfig;
import ru.tinkoff.edu.java.bot.telegram.handlers.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class MyTelegramBotTest {

    @Mock
    ApplicationConfig applicationConfig;

    @Test
    public void unknownCommand(){
        TelegramBotConfig telegramBotConfig = new TelegramBotConfig("123", "123");
        Mockito.when(applicationConfig.telegramBot()).thenReturn(telegramBotConfig);
        MyTelegramBot myTelegramBot = new MyTelegramBot(applicationConfig, null, null);

        String cmd = "/jn3$JRUN#uji4m4i56";

        Update update = new Update();

        Chat chat = new Chat();
        chat.setId(123L);

        Message message = new Message();
        message.setText(cmd);
        message.setChat(chat);

        update.setMessage(message);

        String answer = myTelegramBot
                .getSendMessage(update)
                .getText();
        assertEquals(answer, "Обработчика для данного события не написано, попробуйте ещё раз!");
    }
}