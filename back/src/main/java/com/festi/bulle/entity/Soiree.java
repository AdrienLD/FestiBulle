package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "soiree")
public class Soiree {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soiree_id_gen")
    @SequenceGenerator(name = "soiree_id_gen", sequenceName = "soiree_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "date_heure", nullable = false)
    private Instant dateHeure;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @NotNull
    @Column(name = "nb_places_total", nullable = false)
    private Integer nbPlacesTotal;

    @NotNull
    @Column(name = "nb_places_restantes", nullable = false)
    private Integer nbPlacesRestantes;

    @NotNull
    @Column(name = "est_payante", nullable = false)
    private Boolean estPayante = false;

    @Column(name = "prix", precision = 10, scale = 2)
    private BigDecimal prix;

    @NotNull
    @Column(name = "date_publication", nullable = false)
    private Instant datePublication;

    @NotNull
    @Column(name = "apportez_boissons_aperitifs", nullable = false)
    private Boolean apportezBoissonsAperitifs = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organisateur_id", nullable = false)
    private Utilisateur organisateur;

    @Size(max = 20)
    @NotNull
    @Column(name = "type_soiree", nullable = false, length = 20)
    private String typeSoiree;

    @OneToMany(mappedBy = "soiree")
    private Set<Avi> avis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "soiree")
    private Set<Conversation> conversations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "soiree")
    private Set<Participation> participations = new LinkedHashSet<>();

    @OneToOne(mappedBy = "soiree")
    private Soireeclassique soireeclassique;

    @OneToOne(mappedBy = "soiree")
    private Soireejeuxsociete soireejeuxsociete;

    @OneToOne(mappedBy = "soiree")
    private Soireejeuxvideo soireejeuxvideo;

}