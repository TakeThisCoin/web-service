package ru.tinkoff.edu.java.bot.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.web.dto.requests.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.web.dto.responses.ApiErrorResponse;

import java.util.ArrayList;

@Slf4j
@RestController
public class LinkController {

    public ArrayList<LinkUpdateRequest> updateRequests = new ArrayList<>();

    @PostMapping(path = "/updates")
    @Operation(summary = "Отправить обновление")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Обновление обработано"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                    mediaType = "application/json"), description = "Некорректные параметры запроса")
    })
    public ResponseEntity<Void> createUpdateRequest(@RequestBody @Valid LinkUpdateRequest linkUpdateRequest){
        log.info("ADD - " + linkUpdateRequest.toString());
        updateRequests.add(linkUpdateRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
