package ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat")
public class ChatJpa {
    @Id
    @Getter
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "chat_link",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "link_id"))
    @Getter
    private final Set<LinkJpa> links = new HashSet<>();

    public ChatJpa(Long id) {
        this.id = id;
    }

    public ChatJpa() {
    }

    @Override
    public String toString() {
        return "ChatJpa{" +
                "id=" + id +
                ", links=" + links +
                '}';
    }
}
