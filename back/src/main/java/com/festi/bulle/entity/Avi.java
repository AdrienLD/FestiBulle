package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@Entity
@Table(name = "avis")
public class Avi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avis_id_gen")
    @SequenceGenerator(name = "avis_id_gen", sequenceName = "avis_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "commentaire", length = Integer.MAX_VALUE)
    private String commentaire;

    @NotNull
    @Column(name = "note", nullable = false)
    private Integer note;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "soiree_id", nullable = false)
    private Soiree soiree;

    public Avi() {
        this.dateCreation = Instant.now();
    }

    public Avi(Integer note, Utilisateur utilisateur, Soiree soiree) {
        this();
        this.note = note;
        this.utilisateur = utilisateur;
        this.soiree = soiree;
    }

    public Avi(String commentaire, Integer note, Utilisateur utilisateur, Soiree soiree) {
        this(note, utilisateur, soiree);
        this.commentaire = commentaire;
    }
}