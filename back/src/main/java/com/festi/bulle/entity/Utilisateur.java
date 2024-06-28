package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"adresse", "avis", "messages", "participations", "soirees", "conversations"})
@AllArgsConstructor
@Entity
@Table(name = "utilisateur", indexes = @Index(name = "idx_utilisateur_nom_prenom", columnList = "nom, prenom"))
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @Column(name = "centres_interets")
    private String centresInterets;

    @Column(name = "note_moyenne")
    private Double noteMoyenne;

    @OneToMany(mappedBy = "utilisateur")
    @BatchSize(size = 10)
    private Set<Avi> avis;

    @OneToMany(mappedBy = "utilisateur")
    @BatchSize(size = 50)
    private Set<Message> messages;

    @OneToMany(mappedBy = "utilisateur")
    @BatchSize(size = 10)
    private Set<Participation> participations;

    @OneToMany(mappedBy = "organisateur")
    @BatchSize(size = 10)
    private Set<Soiree> soirees;

    @ManyToMany(mappedBy = "utilisateurs")
    @BatchSize(size = 10)
    private Set<Conversation> conversations;

    public Utilisateur() {
        this.centresInterets = "";
        this.noteMoyenne = 0.0;
        this.avis = new LinkedHashSet<>();
        this.messages = new LinkedHashSet<>();
        this.participations = new LinkedHashSet<>();
        this.soirees = new LinkedHashSet<>();
        this.conversations = new LinkedHashSet<>();
        this.adresse = new Adresse("", "", "", "", "");
        this.dateNaissance = LocalDate.now().minusYears(18);
        this.prenom = "";
    }

    public Utilisateur(String email, String motDePasse, String nom) {
        this();
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}