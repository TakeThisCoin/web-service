package ru.tinkoff.edu.java.scrapper.service.impl.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.chat_link.ChatLinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkDAO;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.LinkDTO;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.service.entities.Link;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor()
public class JdbcLinkService implements LinkService {
    private final LinkDAO linkDAO;
    private final ChatLinkDAO chatLinkDAO;

    @SneakyThrows
    @Override
    public Link add(long tgChatId, URI url) {
        LinkDTO linkDTO;
        if(!linkDAO.contains(url.toString())){
            linkDTO = linkDAO.add(url.toString());
        }else {
            linkDTO = linkDAO.findByUrl(url.toString());
        }
        ChatLinkDTO chatLinkDTO = new ChatLinkDTO(tgChatId, linkDTO.id());
        chatLinkDAO.add(chatLinkDTO);
        return new Link(linkDTO.id(), linkDTO.url(), linkDTO.last_check());
    }

    @SneakyThrows
    @Override
    public Link remove(long tgChatId, URI url) {
        LinkDTO linkDTO = linkDAO.findByUrl(url.toString());
        ChatLinkDTO chatLinkDTO = new ChatLinkDTO(tgChatId, linkDTO.id());
        chatLinkDAO.remove(chatLinkDTO);
        linkDAO.remove(linkDTO);
        return new Link(linkDTO.id(), linkDTO.url(), linkDTO.last_check());
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        List<Link> links = new ArrayList<>();
        for (LinkDTO linkDTO:
                linkDAO.findAll()) {
            Link link = new Link(linkDTO.id(), linkDTO.url(), linkDTO.last_check());
            links.add(link);
        }
        return links;
    }
}
