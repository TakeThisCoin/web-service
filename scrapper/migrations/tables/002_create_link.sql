--liquibase formatted sql

--changeset TakeThisCoin:2
CREATE TABLE IF NOT EXISTS link(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    url text  NOT NULL unique,
    last_check timestamp with time zone DEFAULT now() NOT NULL
);