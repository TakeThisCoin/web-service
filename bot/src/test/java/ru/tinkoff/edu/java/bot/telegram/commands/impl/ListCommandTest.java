package ru.tinkoff.edu.java.bot.telegram.commands.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.tinkoff.edu.java.bot.dto.responses.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.responses.ListLinksResponse;
import ru.tinkoff.edu.java.bot.webclients.ScrapperClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListCommandTest {
    private static final Long USER_ID = 111111111L;
    private static Update update;

    @Mock
    ScrapperClient scrapperClient;
    @InjectMocks
    ListCommand listCommand;

    @BeforeAll
    static void setUp() {
        User user = new User();
        user.setId(USER_ID);

        Chat chat = new Chat();
        chat.setId(USER_ID);

        Message message = new Message();
        message.setFrom(user);
        message.setChat(chat);
        message.setText("/list");

        update = new Update();
        update.setMessage(message);
    }

    @Test
    public void testEmpty(){
        Mockito.when(scrapperClient.getLinks(USER_ID)).thenReturn(new ListLinksResponse(new ArrayList<LinkResponse>(), 0));

        String message = listCommand.execute(update).getText();
        Assertions.assertEquals("Список ссылок пуст!", message);
    }

    @Test
    public void testNotEmpty(){
        try {
            List<LinkResponse> linkResponseList = new ArrayList<>();
            linkResponseList.add(new LinkResponse(USER_ID, new URI("https://go.go")));
            ListLinksResponse listLinksResponse = new ListLinksResponse(linkResponseList, linkResponseList.size());
            Mockito
                    .when(scrapperClient.getLinks(USER_ID))
                    .thenReturn(listLinksResponse);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String message = listCommand.execute(update).getText();

        Assertions.assertEquals("\nLinkResponse[id=111111111, url=https://go.go]", message);
    }


}