package ru.tinkoff.edu.java.scrapper.service.impl.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.OffsetTime;
import java.util.Set;

@Entity
@Table(name = "link")
public class LinkJpa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "url", nullable = false)
    private String url;

    @Override
    public String toString() {
        return "LinkJpa{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", lastCheck=" + lastCheck +
                ", chats=" + chats +
                '}';
    }

    @Getter @Setter
    @Column(name = "last_check", nullable = false, insertable = false)
    private Timestamp lastCheck;

    @ManyToMany(mappedBy = "links")
    @Getter
    private Set<ChatJpa> chats;

    public LinkJpa(String url) {
        this.url = url;
    }

    public LinkJpa() {
    }
}
