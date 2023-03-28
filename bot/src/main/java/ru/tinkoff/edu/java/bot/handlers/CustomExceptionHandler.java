package ru.tinkoff.edu.java.bot.handlers;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.dto.responses.ApiErrorResponse;

import java.util.ArrayList;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Некорректные параметры запроса", "400", "MethodArgumentNotValidException", exception.getMessage(), new ArrayList<>()));
    }

}
