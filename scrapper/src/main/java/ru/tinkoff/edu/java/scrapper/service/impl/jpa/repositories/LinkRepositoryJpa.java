package ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.LinkJpa;

import java.util.Optional;

public interface LinkRepositoryJpa extends CrudRepository<LinkJpa, Long> {
    Optional<LinkJpa> findByUrl(String url);
}
