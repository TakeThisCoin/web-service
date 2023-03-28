package ru.tinkoff.edu.java.scrapper.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record AddLinkRequest(@NotNull @Pattern(regexp = "(([\\w]+:)?//)?(([\\d\\w]|%[a-fA-f\\d]{2,2})+(:([\\d\\w]|%[a-fA-f\\d]{2,2})+)?@)?([\\d\\w][-\\d\\w]{0,253}[\\d\\w]\\.)+[\\w]{2,63}(:[\\d]+)?(/([-+_~.\\d\\w]|%[a-fA-f\\d]{2,2})*)*(\\?(&?([-+_~.\\d\\w]|%[a-fA-f\\d]{2,2})=?)*)?(#([-+_~.\\d\\w]|%[a-fA-f\\d]{2,2})*)?") String link) {
}
