package ru.tinkoff.edu.java.scrapper.domain.dao.link;

import ru.tinkoff.edu.java.scrapper.domain.dto.GitHubRepositoryIssueDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;

import java.sql.Timestamp;
import java.util.List;

public interface LinkDAO {
    LinkDTO add(String link);
    LinkDTO findByUrl(String link);
    void remove(LinkDTO linkDTO);
    boolean contains(String link);
    List<LinkDTO> findAll();
    List<LinkDTO> findAllOutdated(Timestamp before);
    LinkDTO updateLastCheck(LinkDTO linkDTO);
}
