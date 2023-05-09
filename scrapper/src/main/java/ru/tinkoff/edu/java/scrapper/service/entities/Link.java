package ru.tinkoff.edu.java.scrapper.service.entities;

import java.net.URI;
import java.sql.Timestamp;

public record Link(long id, URI url, Timestamp lastCheck) {
}
