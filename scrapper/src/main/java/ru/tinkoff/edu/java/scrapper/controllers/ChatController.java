package ru.tinkoff.edu.java.scrapper.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.github.responses.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.dto.stackexchange.responses.StackExchangeQuestionsResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.NotExists404Exception;

import java.util.ArrayList;

@RestController
@RequestMapping("/tg-chat")
public class ChatController {

    public ArrayList<Long> chatIds = new ArrayList<>();

    @PostMapping("/{id}")
    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Чат зарегистрирован"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public void createChat(@PathVariable long id){
        chatIds.add(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить чат")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Чат успешно удалён"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Чат не существует", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"))
    })
    public void deleteChat(@PathVariable long id){
        if(!chatIds.contains(id))
            throw new NotExists404Exception("Чат не существует", "NotExists404Exception", "Чат с id = " + id + ", не существует!", new ArrayList<>());
        chatIds.remove(id);
    }
}
