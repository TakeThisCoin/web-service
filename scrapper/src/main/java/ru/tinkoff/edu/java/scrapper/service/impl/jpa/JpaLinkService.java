package ru.tinkoff.edu.java.scrapper.service.impl.jpa;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.entities.Link;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.ChatJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities.LinkJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.ChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.service.impl.jpa.repositories.LinkRepositoryJpa;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor()
public class JpaLinkService implements LinkService {
    private final LinkRepositoryJpa linkRepository;
    private final ChatRepositoryJpa chatRepositoryJpa;

    @SneakyThrows
    @Override
    public Link add(long tgChatId, URI url) {
        ChatJpa chatJpa = chatRepositoryJpa.findById(tgChatId).orElseThrow();
        LinkJpa linkJpa;
        if(linkRepository.findByUrl(url.toString()).isEmpty()){
            linkJpa = new LinkJpa(url.toString());
            linkRepository.save(linkJpa);
        }

        linkJpa = linkRepository.findByUrl(url.toString()).orElseThrow();

        chatJpa.getLinks().add(linkJpa);
        chatRepositoryJpa.save(chatJpa);

        return new Link(linkJpa.getId(), new URI(linkJpa.getUrl()), linkJpa.getLastCheck());
    }

    @SneakyThrows
    @Override
    public Link remove(long tgChatId, URI url) {
        ChatJpa chatJpa = chatRepositoryJpa.findById(tgChatId).orElseThrow();
        LinkJpa linkJpa = linkRepository.findByUrl(url.toString()).orElseThrow();
        chatJpa.getLinks().remove(linkJpa);
        chatRepositoryJpa.save(chatJpa);

        return new Link(linkJpa.getId(), new URI(linkJpa.getUrl()), linkJpa.getLastCheck());
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        List<Link> links = new ArrayList<>();
        for (LinkJpa link :
                chatRepositoryJpa.findById(tgChatId).orElseThrow().getLinks()) {
            try {
                links.add(new Link(link.getId(), new URI(link.getUrl()), link.getLastCheck()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return links;
    }
}
