package ru.tinkoff.edu.java.bot.web.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.web.dto.responses.ApiErrorResponse;

import java.util.Arrays;

@RestControllerAdvice
public class CustomExceptionAdvice {

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiErrorResponse> handleKnownException(Exception exception){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Некорректные параметры запроса", "400", exception.getClass().getName(), exception.getMessage(), Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiErrorResponse> handleUnknownException(Exception exception){
        return new ResponseEntity<ApiErrorResponse>(
                new ApiErrorResponse("Неизвестная ошибка", "500", exception.getClass().getName(), exception.getMessage(), Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
