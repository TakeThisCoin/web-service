--liquibase formatted sql

--changeset TakeThisCoin:3
CREATE TABLE IF NOT EXISTS "chat_link"(
    chat_id bigint NOT NULL,
    link_id bigint NOT NULL,
    CONSTRAINT "chat_link_chat_id_fkey" FOREIGN KEY (chat_id)
        REFERENCES chat (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "chat_link_link_id_fkey" FOREIGN KEY (link_id)
        REFERENCES link (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT "uq_chat-link" UNIQUE(chat_id, link_id),
	PRIMARY KEY (chat_id, link_id)
);