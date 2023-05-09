--liquibase formatted sql

--changeset TakeThisCoin:4
CREATE TABLE IF NOT EXISTS "github_repository_issue"(
    id bigint PRIMARY KEY,
    link_id bigint NOT NULL,
    state text  NOT NULL,
    CONSTRAINT "github_repository_issue_link_id_fkey" FOREIGN KEY (link_id)
        REFERENCES link (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT "uq_github_repository_issue" UNIQUE(id, link_id)
);