package ru.tinkoff.edu.java.scrapper.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.links.Link;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.requests.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.dto.responses.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.responses.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.NotExists404Exception;

import java.util.ArrayList;

@RestController
@RequestMapping("/links")
public class LinkController {

    ArrayList<LinkResponse> links = new ArrayList<LinkResponse>();

    @GetMapping()
    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ссылки успешно получены", content = @Content(schema = @Schema(implementation = ListLinksResponse.class),
                    mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public ListLinksResponse requestLinks(@RequestHeader("Tg-Chat-Id") long tgChatId){
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
        LinkResponse linkResponse = new LinkResponse(tgChatId, link.link());
        links.add(linkResponse);
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
        LinkResponse linkWhoWillBeDeleted = null;
        for (LinkResponse linkResponse: links) {
            if(linkResponse.url().compareTo(removeLinkRequest.link()) == 0){
                linkWhoWillBeDeleted = linkResponse;
                break;
            }
        }

        links.remove(linkWhoWillBeDeleted);

        if(linkWhoWillBeDeleted == null)
            throw new NotExists404Exception(removeLinkRequest.link().toString());
        else
            return linkWhoWillBeDeleted;
    }
}
