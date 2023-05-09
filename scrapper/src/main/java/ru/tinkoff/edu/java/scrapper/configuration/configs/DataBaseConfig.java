package ru.tinkoff.edu.java.scrapper.configuration.configs;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record DataBaseConfig (@NotBlank String driverClassName,
                              @NotBlank String url,
                              @NotBlank String userName,
                              @NotBlank String password
                              ){
}
