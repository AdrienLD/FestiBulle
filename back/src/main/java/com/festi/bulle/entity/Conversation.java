package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Boolean estGroupe = false;

    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "utilisateur_conversation",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
    private Set<Utilisateur> utilisateurs = new LinkedHashSet<>();

}