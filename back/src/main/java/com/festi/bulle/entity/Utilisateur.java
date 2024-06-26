package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_id_gen")
    @SequenceGenerator(name = "utilisateur_id_gen", sequenceName = "utilisateur_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Size(max = 100)
    @NotNull
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Size(max = 100)
    @NotNull
    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @Column(name = "centres_interets")
    @ElementCollection
    private List<String> centresInterets;

    @Column(name = "note_moyenne")
    private Double noteMoyenne;

    @OneToMany(mappedBy = "utilisateur")
    private Set<Avi> avis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Message> messages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Participation> participations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisateur")
    private Set<Soiree> soirees = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "utilisateurs")
    private Set<Conversation> conversations = new LinkedHashSet<>();

}