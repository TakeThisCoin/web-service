package ru.tinkoff.edu.java.bot.webclients;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.bot.dto.requests.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.responses.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.responses.ListLinksResponse;

public interface ScrapperClient {

    @GetExchange("/links")
    ListLinksResponse getLinks(@RequestHeader(name = "Tg-Chat-Id") long tgChatId);

    @PostExchange("/links")
    LinkResponse addLink(@RequestHeader(name = "Tg-Chat-Id") long tgChatId, @RequestBody AddLinkRequest link);


    @DeleteExchange("/links")
    LinkResponse deleteLink(@RequestHeader(name = "Tg-Chat-Id") long tgChatId, @RequestBody RemoveLinkRequest removeLinkRequest);


    @PostExchange("/tg-chat/{id}")
    void addTgChat(@PathVariable(value = "id") long tgChatId);

    @DeleteExchange("/tg-chat/{id}")
    void deleteTgChat(@PathVariable(value = "id") long tgChatId);
}
