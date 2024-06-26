package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"soiree", "messages", "utilisateurs"})
@AllArgsConstructor
@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversation_id_gen")
    @SequenceGenerator(name = "conversation_id_gen", sequenceName = "conversation_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soiree_id")
    private Soiree soiree;

    @NotNull
    @Column(name = "est_groupe", nullable = false)
    private Boolean estGroupe;

    @OneToMany(mappedBy = "conversation")
    @BatchSize(size = 50)
    private Set<Message> messages;

    @ManyToMany
    @JoinTable(name = "utilisateur_conversation",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
    @BatchSize(size = 10)
    private Set<Utilisateur> utilisateurs;

    public Conversation() {
        this.dateCreation = Instant.now();
        this.estGroupe = false;
        this.messages = new LinkedHashSet<>();
        this.utilisateurs = new LinkedHashSet<>();
    }

    public Conversation(boolean estGroupe) {
        this();
        this.estGroupe = estGroupe;
    }

    public Conversation(Soiree soiree, boolean estGroupe) {
        this(estGroupe);
        this.soiree = soiree;
    }

    public void addUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
        utilisateur.getConversations().add(this);
    }

    public void removeUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        utilisateur.getConversations().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conversation)) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}