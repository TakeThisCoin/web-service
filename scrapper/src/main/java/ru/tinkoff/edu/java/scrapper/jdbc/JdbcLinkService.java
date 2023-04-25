package ru.tinkoff.edu.java.scrapper.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.Collection;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcLinkService implements LinkService {
    private final LinkDAO linkDAO;
    private final ChatLinkDAO chatLinkDAO;

    @Override
    public LinkDTO add(long tgChatId, URI url) {
        LinkDTO linkDTO = linkDAO.add(url.toString());
        ChatLinkDTO chatLinkDTO = new ChatLinkDTO(tgChatId, linkDTO.id());
        chatLinkDAO.add(chatLinkDTO);
        return linkDTO;
    }

    @Override
    public LinkDTO remove(long tgChatId, URI url) {
        LinkDTO linkDTO = linkDAO.findByUrl(url.toString());
        ChatLinkDTO chatLinkDTO = new ChatLinkDTO(tgChatId, linkDTO.id());
        chatLinkDAO.remove(chatLinkDTO);
        linkDAO.remove(linkDTO);
        return linkDTO;
    }

    @Override
    public Collection<LinkDTO> listAll(long tgChatId) {
        return linkDAO.findAll();
    }
}
