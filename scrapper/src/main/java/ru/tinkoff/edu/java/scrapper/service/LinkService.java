package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.net.URI;
import java.util.Collection;

public interface LinkService {
    LinkDTO add(long tgChatId, URI url);
    LinkDTO remove(long tgChatId, URI url);
    Collection<LinkDTO> listAll(long tgChatId);
}
