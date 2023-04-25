package ru.tinkoff.edu.java.scrapper.domain.dao.link;

import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.util.List;

public interface LinkDAO {
    LinkDTO add(String link);
    LinkDTO findByUrl(String link);
    void remove(LinkDTO linkDTO);
    List<LinkDTO> findAll();
}
