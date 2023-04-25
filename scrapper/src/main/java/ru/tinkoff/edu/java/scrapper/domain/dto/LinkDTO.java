package ru.tinkoff.edu.java.scrapper.domain.dto;

import java.net.URI;
import java.sql.Timestamp;

public record LinkDTO (long id, URI url, Timestamp last_check){
}
