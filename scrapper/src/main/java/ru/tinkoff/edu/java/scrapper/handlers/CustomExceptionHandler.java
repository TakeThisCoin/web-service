package ru.tinkoff.edu.java.scrapper.handlers;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.PropertyAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.scrapper.dto.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.NotExists404Exception;

import java.util.ArrayList;
import java.util.Arrays;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Некорректные параметры запроса", "400", "MethodArgumentTypeMismatchException", exception.getMessage(), new ArrayList<>()));
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException exception){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Некорректные параметры запроса", "400", "MissingRequestHeaderException", exception.getMessage(), new ArrayList<>()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(new ApiErrorResponse("Некорректные параметры запроса", "400", "MethodArgumentNotValidException", exception.getMessage(), new ArrayList<>()));
    }

    @ExceptionHandler({NotExists404Exception.class})
    public ResponseEntity<ApiErrorResponse> handleDataNotCorrect(NotExists404Exception exception){
        ResponseEntity<ApiErrorResponse> responseEntity = new ResponseEntity<ApiErrorResponse>(new ApiErrorResponse(exception.getDescription(), "404", "NotExists404Exception", exception.getExceptionMessage(), exception.getStacktrace()), HttpStatusCode.valueOf(404));
        return responseEntity;
    }
}
