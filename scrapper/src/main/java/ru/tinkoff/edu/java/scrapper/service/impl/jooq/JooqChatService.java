package ru.tinkoff.edu.java.scrapper.service.impl.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.Chat;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.records.ChatRecord;

@RequiredArgsConstructor()
public class JooqChatService implements ChatService{
    private final DSLContext dslContext;

    @Override
    public void register(long tgChatId) {
        ChatRecord chatRecord = dslContext.newRecord(Chat.CHAT);
        chatRecord.setId(tgChatId);
        chatRecord.store();
    }

    @Override
    public void unregister(long tgChatId) {
        ChatRecord chatRecord = dslContext.fetchOne(Chat.CHAT, Chat.CHAT.ID.eq(tgChatId));
        if (chatRecord != null)
            chatRecord.delete();
    }
}
