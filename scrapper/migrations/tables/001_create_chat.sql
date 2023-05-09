--liquibase formatted sql

--changeset TakeThisCoin:1
CREATE TABLE IF NOT EXISTS chat(
    id bigint PRIMARY KEY
);