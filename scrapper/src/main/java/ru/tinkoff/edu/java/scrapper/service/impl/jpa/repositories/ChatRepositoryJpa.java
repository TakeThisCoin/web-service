package ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.ChatJpa;

public interface ChatRepositoryJpa extends CrudRepository<ChatJpa, Long> {
}
