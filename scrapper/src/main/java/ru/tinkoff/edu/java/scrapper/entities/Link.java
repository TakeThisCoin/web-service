package ru.tinkoff.edu.java.scrapper.entities;

import java.net.URI;
import java.sql.Timestamp;

public record Link(long id, URI url, Timestamp lastCheck) {
}
