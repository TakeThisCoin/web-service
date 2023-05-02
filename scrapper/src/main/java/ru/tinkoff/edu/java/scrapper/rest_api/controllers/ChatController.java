package ru.tinkoff.edu.java.scrapper.rest_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.rest_api.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.JpaChatService;

@RestController
@RequestMapping("/tg-chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/{id}")
    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Чат зарегистрирован"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public void createChat(@PathVariable long id){
        chatService.register(id);
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
        chatService.unregister(id);
    }
}
