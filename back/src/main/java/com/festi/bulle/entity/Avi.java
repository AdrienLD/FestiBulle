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
    private int note;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soiree_id")
    private Soiree soiree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private Utilisateur toUserId;

    public Avi() {
        this.dateCreation = Instant.now();
    }

    public Avi(Integer note, Utilisateur utilisateur, Soiree soiree, Utilisateur toUserId) {
        this();
        this.note = note;
        this.utilisateur = utilisateur;
        this.soiree = soiree;
        this.toUserId = toUserId;
    }

    public Avi(String commentaire, Integer note, Utilisateur utilisateur, Soiree soiree, Utilisateur toUserId) {
        this(note, utilisateur, soiree, toUserId);
        this.commentaire = commentaire;
    }
}