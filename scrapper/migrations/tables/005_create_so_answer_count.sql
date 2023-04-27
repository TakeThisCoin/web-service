--liquibase formatted sql

--changeset TakeThisCoin:5
CREATE TABLE IF NOT EXISTS "so_answer_count"(
    link_id bigint NOT NULL PRIMARY KEY,
    count bigint  NOT NULL,
    CONSTRAINT "so_answer_count_link_id_fkey" FOREIGN KEY (link_id)
        REFERENCES link (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);