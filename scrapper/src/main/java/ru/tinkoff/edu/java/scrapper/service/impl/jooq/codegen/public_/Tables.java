/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_;


import javax.annotation.processing.Generated;

import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.Chat;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.ChatLink;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.Databasechangelog;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.Databasechangeloglock;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.GithubRepositoryIssue;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.Link;
import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.SoAnswerCount;


/**
 * Convenience access to all tables in public.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.chat</code>.
     */
    public static final Chat CHAT = Chat.CHAT;

    /**
     * The table <code>public.chat_link</code>.
     */
    public static final ChatLink CHAT_LINK = ChatLink.CHAT_LINK;

    /**
     * The table <code>public.databasechangelog</code>.
     */
    public static final Databasechangelog DATABASECHANGELOG = Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>public.databasechangeloglock</code>.
     */
    public static final Databasechangeloglock DATABASECHANGELOGLOCK = Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>public.github_repository_issue</code>.
     */
    public static final GithubRepositoryIssue GITHUB_REPOSITORY_ISSUE = GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE;

    /**
     * The table <code>public.link</code>.
     */
    public static final Link LINK = Link.LINK;

    /**
     * The table <code>public.so_answer_count</code>.
     */
    public static final SoAnswerCount SO_ANSWER_COUNT = SoAnswerCount.SO_ANSWER_COUNT;
}
