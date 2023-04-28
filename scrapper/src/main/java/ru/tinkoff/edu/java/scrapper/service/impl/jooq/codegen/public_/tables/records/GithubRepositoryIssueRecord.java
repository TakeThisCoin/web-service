/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.records;


import java.beans.ConstructorProperties;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.GithubRepositoryIssue;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GithubRepositoryIssueRecord extends UpdatableRecordImpl<GithubRepositoryIssueRecord> implements Record3<Long, Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.github_repository_issue.id</code>.
     */
    public void setId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.github_repository_issue.id</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.github_repository_issue.link_id</code>.
     */
    public void setLinkId(@NotNull Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.github_repository_issue.link_id</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinkId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.github_repository_issue.state</code>.
     */
    public void setState(@NotNull String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.github_repository_issue.state</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public String getState() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row3<Long, Long, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row3<Long, Long, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE.ID;
    }

    @Override
    @NotNull
    public Field<Long> field2() {
        return GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE.LINK_ID;
    }

    @Override
    @NotNull
    public Field<String> field3() {
        return GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE.STATE;
    }

    @Override
    @NotNull
    public Long component1() {
        return getId();
    }

    @Override
    @NotNull
    public Long component2() {
        return getLinkId();
    }

    @Override
    @NotNull
    public String component3() {
        return getState();
    }

    @Override
    @NotNull
    public Long value1() {
        return getId();
    }

    @Override
    @NotNull
    public Long value2() {
        return getLinkId();
    }

    @Override
    @NotNull
    public String value3() {
        return getState();
    }

    @Override
    @NotNull
    public GithubRepositoryIssueRecord value1(@NotNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public GithubRepositoryIssueRecord value2(@NotNull Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public GithubRepositoryIssueRecord value3(@NotNull String value) {
        setState(value);
        return this;
    }

    @Override
    @NotNull
    public GithubRepositoryIssueRecord values(@NotNull Long value1, @NotNull Long value2, @NotNull String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GithubRepositoryIssueRecord
     */
    public GithubRepositoryIssueRecord() {
        super(GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE);
    }

    /**
     * Create a detached, initialised GithubRepositoryIssueRecord
     */
    @ConstructorProperties({ "id", "linkId", "state" })
    public GithubRepositoryIssueRecord(@NotNull Long id, @NotNull Long linkId, @NotNull String state) {
        super(GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE);

        setId(id);
        setLinkId(linkId);
        setState(state);
    }

    /**
     * Create a detached, initialised GithubRepositoryIssueRecord
     */
    public GithubRepositoryIssueRecord(ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.pojos.GithubRepositoryIssue value) {
        super(GithubRepositoryIssue.GITHUB_REPOSITORY_ISSUE);

        if (value != null) {
            setId(value.getId());
            setLinkId(value.getLinkId());
            setState(value.getState());
        }
    }
}