package ru.tinkoff.edu.java.scrapper.advices;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.scrapper.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.NotExists404Exception;

import java.util.Arrays;

@RestControllerAdvice
public class CustomExceptionAdvice {

    @ExceptionHandler(value = {
            NotExists404Exception.class,
    })
    public ResponseEntity<ApiErrorResponse> handle404Exception(Exception exception){
        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        "Ресурс не был найден",
                        "404",
                        exception.getClass().getName(),
                        exception.getMessage(),
                        Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList())
        );
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ApiErrorResponse> handle400Exception(Exception exception){
        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                    "Некорректные параметры запроса",
                    "400",
                    exception.getClass().getName(),
                    exception.getMessage(),
                    Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList())
        );
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiErrorResponse> handleUnknownException(Exception exception){
        return new ResponseEntity<ApiErrorResponse>(
                new ApiErrorResponse(
                        "Неизвестная ошибка",
                        "500",
                        exception.getClass().getName(),
                        exception.getMessage(),
                        Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
