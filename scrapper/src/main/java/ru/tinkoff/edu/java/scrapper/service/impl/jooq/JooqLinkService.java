package ru.tinkoff.edu.java.scrapper.service.impl.jooq;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.entities.Link;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.ChatLink;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.records.ChatLinkRecord;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.records.LinkRecord;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.Link.LINK;

@Slf4j
@RequiredArgsConstructor()
public class JooqLinkService implements LinkService {
    private final DSLContext dslContext;

    @SneakyThrows
    @Override
    public Link add(long tgChatId, URI url) {
        boolean exist = dslContext.fetchExists(LINK, LINK.URL.eq(url.toString()));

        LinkRecord linkRecord;
        if(exist){
            linkRecord = dslContext.fetchOne(LINK, LINK.URL.eq(url.toString()));
        }else {
            linkRecord = dslContext.newRecord(LINK);
            linkRecord.setUrl(url.toString());
            linkRecord.store();
            linkRecord.refresh();
        }

        ChatLinkRecord chatLinkRecord = dslContext.newRecord(ChatLink.CHAT_LINK);
        chatLinkRecord.setLinkId(linkRecord.getId());
        chatLinkRecord.setChatId(tgChatId);
        chatLinkRecord.store();

        return new Link(
                linkRecord.getId(),
                new URI(linkRecord.getUrl()),
                new Timestamp(linkRecord.getLastCheck().toEpochSecond() * 1000)
        );
    }

    @SneakyThrows
    @Override
    public Link remove(long tgChatId, URI url) {
        Link link = null;

        LinkRecord linkRecord = dslContext.fetchOne(LINK, LINK.URL.eq(url.toString()));
        if(linkRecord == null)
            return link;

        ChatLinkRecord chatLinkRecord = dslContext.fetchOne(ChatLink.CHAT_LINK, ChatLink.CHAT_LINK.CHAT_ID.eq(tgChatId), ChatLink.CHAT_LINK.LINK_ID.eq(linkRecord.getId()));
        if(chatLinkRecord == null)
            return link;

        link = new Link(linkRecord.getId(), new URI(linkRecord.getUrl()), new Timestamp(linkRecord.getLastCheck().toEpochSecond() * 1000));

        chatLinkRecord.delete();

        return link;
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        List<Link> links = new ArrayList<>();
        for (LinkRecord linkRecord : dslContext.fetch(LINK)) {
            try {
                links.add(
                        new Link
                                (
                                        linkRecord.getId(),
                                        new URI(linkRecord.getUrl()),
                                        new Timestamp(linkRecord.getLastCheck().toEpochSecond() * 1000)
                                )
                );
            } catch (URISyntaxException | NullPointerException e) {
                e.printStackTrace();
                log.warn("Skip linkRecord: " + linkRecord.getId() + ", cause: " + e.getClass().getSimpleName());
            }

        }
        return links;
    }
}
