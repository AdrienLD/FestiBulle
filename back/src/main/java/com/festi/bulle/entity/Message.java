package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = {"utilisateur", "conversation"})
@Entity
@Table(name = "message", indexes = @Index(name = "idx-conversation", columnList = "conversation_id"))
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_gen")
    @SequenceGenerator(name = "message_id_gen", sequenceName = "message_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "contenu", nullable = false, length = Integer.MAX_VALUE)
    private String contenu;

    @NotNull
    @Column(name = "date_envoi", nullable = false)
    private Instant dateEnvoi;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    public Message() {
        this.dateEnvoi = Instant.now();
    }

    public Message(String contenu, Utilisateur utilisateur, Conversation conversation) {
        this();
        this.contenu = contenu;
        this.utilisateur = utilisateur;
        this.conversation = conversation;
    }

    public void addToConversation() {
        this.conversation.getMessages().add(this);
    }

    public void removeFromConversation() {
        this.conversation.getMessages().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getId(), message.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}