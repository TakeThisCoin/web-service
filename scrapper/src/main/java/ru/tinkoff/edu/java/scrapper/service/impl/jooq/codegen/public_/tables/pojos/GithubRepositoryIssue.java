/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen.public_.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;


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
public class GithubRepositoryIssue implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long linkId;
    private String state;

    public GithubRepositoryIssue() {}

    public GithubRepositoryIssue(GithubRepositoryIssue value) {
        this.id = value.id;
        this.linkId = value.linkId;
        this.state = value.state;
    }

    @ConstructorProperties({ "id", "linkId", "state" })
    public GithubRepositoryIssue(
        @NotNull Long id,
        @NotNull Long linkId,
        @NotNull String state
    ) {
        this.id = id;
        this.linkId = linkId;
        this.state = state;
    }

    /**
     * Getter for <code>public.github_repository_issue.id</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.github_repository_issue.id</code>.
     */
    public void setId(@NotNull Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.github_repository_issue.link_id</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinkId() {
        return this.linkId;
    }

    /**
     * Setter for <code>public.github_repository_issue.link_id</code>.
     */
    public void setLinkId(@NotNull Long linkId) {
        this.linkId = linkId;
    }

    /**
     * Getter for <code>public.github_repository_issue.state</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public String getState() {
        return this.state;
    }

    /**
     * Setter for <code>public.github_repository_issue.state</code>.
     */
    public void setState(@NotNull String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final GithubRepositoryIssue other = (GithubRepositoryIssue) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.linkId == null) {
            if (other.linkId != null)
                return false;
        }
        else if (!this.linkId.equals(other.linkId))
            return false;
        if (this.state == null) {
            if (other.state != null)
                return false;
        }
        else if (!this.state.equals(other.state))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.linkId == null) ? 0 : this.linkId.hashCode());
        result = prime * result + ((this.state == null) ? 0 : this.state.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GithubRepositoryIssue (");

        sb.append(id);
        sb.append(", ").append(linkId);
        sb.append(", ").append(state);

        sb.append(")");
        return sb.toString();
    }
}
