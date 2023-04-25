package ru.tinkoff.edu.java.scrapper.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;
import ru.tinkoff.edu.java.scrapper.dto.requests.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.requests.BotUpdatesRequest;
import ru.tinkoff.edu.java.scrapper.dto.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.dto.responses.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.responses.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.NotExists404Exception;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.webclients.BotClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping()
    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ссылки успешно получены", content = @Content(schema = @Schema(implementation = ListLinksResponse.class),
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public ListLinksResponse requestLinks(@RequestHeader("Tg-Chat-Id") long tgChatId){
        ArrayList<LinkResponse> links = new ArrayList<LinkResponse>();
        Collection<LinkDTO> linksDTO = linkService.listAll(tgChatId);
        for (LinkDTO link :
                linksDTO) {
            links.add(new LinkResponse(link.id(), link.url()));
        }
        
        return new ListLinksResponse(links, links.size());
    }

    @PostMapping()
    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ссылка успешно добавлена", content = @Content(schema = @Schema(implementation = LinkResponse.class),
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public LinkResponse createLink(@RequestHeader("Tg-Chat-Id") long tgChatId, @RequestBody @Valid AddLinkRequest link){
        LinkDTO linkDTO = linkService.add(tgChatId, link.link());
        LinkResponse linkResponse = new LinkResponse(linkDTO.id(), linkDTO.url());

        return linkResponse;
    }

    @DeleteMapping()
    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ссылка успешно убрана", content = @Content(schema = @Schema(implementation = LinkResponse.class),
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Ссылка не найдена", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"))
    })
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") long tgChatId, @RequestBody @Valid RemoveLinkRequest removeLinkRequest){
        LinkDTO linkDTO = linkService.remove(tgChatId, removeLinkRequest.link());
        LinkResponse response = new LinkResponse(linkDTO.id(), linkDTO.url());

        return response;
    }
}
