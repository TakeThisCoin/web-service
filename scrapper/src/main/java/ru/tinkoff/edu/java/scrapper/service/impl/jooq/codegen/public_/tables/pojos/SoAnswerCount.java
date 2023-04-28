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
public class SoAnswerCount implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long linkId;
    private Long count;

    public SoAnswerCount() {}

    public SoAnswerCount(SoAnswerCount value) {
        this.linkId = value.linkId;
        this.count = value.count;
    }

    @ConstructorProperties({ "linkId", "count" })
    public SoAnswerCount(
        @NotNull Long linkId,
        @NotNull Long count
    ) {
        this.linkId = linkId;
        this.count = count;
    }

    /**
     * Getter for <code>public.so_answer_count.link_id</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinkId() {
        return this.linkId;
    }

    /**
     * Setter for <code>public.so_answer_count.link_id</code>.
     */
    public void setLinkId(@NotNull Long linkId) {
        this.linkId = linkId;
    }

    /**
     * Getter for <code>public.so_answer_count.count</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getCount() {
        return this.count;
    }

    /**
     * Setter for <code>public.so_answer_count.count</code>.
     */
    public void setCount(@NotNull Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SoAnswerCount other = (SoAnswerCount) obj;
        if (this.linkId == null) {
            if (other.linkId != null)
                return false;
        }
        else if (!this.linkId.equals(other.linkId))
            return false;
        if (this.count == null) {
            if (other.count != null)
                return false;
        }
        else if (!this.count.equals(other.count))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.linkId == null) ? 0 : this.linkId.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SoAnswerCount (");

        sb.append(linkId);
        sb.append(", ").append(count);

        sb.append(")");
        return sb.toString();
    }
}