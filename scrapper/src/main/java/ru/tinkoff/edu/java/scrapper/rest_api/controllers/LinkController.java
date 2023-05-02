package ru.tinkoff.edu.java.scrapper.rest_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.service.entities.Link;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.JooqLinkService;
import ru.tinkoff.edu.java.scrapper.rest_api.dto.requests.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.rest_api.dto.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.rest_api.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.rest_api.dto.responses.LinkResponse;
import ru.tinkoff.edu.java.scrapper.rest_api.dto.responses.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.JpaLinkService;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService){
        this.linkService = linkService;
    }

    @GetMapping()
    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ссылки успешно получены", content = @Content(schema = @Schema(implementation = ListLinksResponse.class),
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public ListLinksResponse requestLinks(@RequestHeader("Tg-Chat-Id") long tgChatId){
        ArrayList<LinkResponse> linksResponse = new ArrayList<LinkResponse>();
        Collection<Link> links = linkService.listAll(tgChatId);
        for (Link link :
                links) {
            linksResponse.add(new LinkResponse(link.id(), link.url()));
        }
        
        return new ListLinksResponse(linksResponse, links.size());
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
        Link linkEntity = linkService.add(tgChatId, link.link());
        LinkResponse linkResponse = new LinkResponse(linkEntity.id(), linkEntity.url());

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
        Link link = linkService.remove(tgChatId, removeLinkRequest.link());
        LinkResponse response = new LinkResponse(link.id(), link.url());

        return response;
    }
}
