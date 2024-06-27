package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
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
    private Date dateHeure;

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
    private Boolean estPayante;

    @Column(name = "prix", precision = 10, scale = 2)
    private BigDecimal prix;

    @NotNull
    @Column(name = "date_publication", nullable = false)
    private Date datePublication;

    @NotNull
    @Column(name = "apportez_boissons_aperitifs", nullable = false)
    private Boolean apportezBoissonsAperitifs;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organisateur_id", nullable = false)
    private Utilisateur organisateur;

    @Size(max = 20)
    @NotNull
    @Column(name = "type_soiree", nullable = false, length = 20)
    private String typeSoiree;

    @OneToMany(mappedBy = "soiree")
    private Set<Avi> avis;

    @OneToMany(mappedBy = "soiree")
    private Set<Conversation> conversations;

    @OneToMany(mappedBy = "soiree")
    private Set<Participation> participations;

    @OneToOne(mappedBy = "soiree")
    private Soireeclassique soireeclassique;

    @OneToOne(mappedBy = "soiree")
    private Soireejeuxsociete soireejeuxsociete;

    @OneToOne(mappedBy = "soiree")
    private Soireejeuxvideo soireejeuxvideo;

    public Soiree() {
        this.datePublication = new Date();
        this.avis = new LinkedHashSet<>();
        this.conversations = new LinkedHashSet<>();
        this.participations = new LinkedHashSet<>();
    }

    public Soiree(String nom, Date dateHeure, Adresse adresse, Integer nbPlacesTotal, Utilisateur organisateur, String typeSoiree, Boolean estPayante, Boolean apportezBoissonsAperitifs) {
        this();
        this.nom = nom;
        this.dateHeure = dateHeure;
        this.adresse = adresse;
        this.nbPlacesTotal = nbPlacesTotal;
        this.nbPlacesRestantes = 99999;
        this.organisateur = organisateur;
        this.typeSoiree = typeSoiree;
        this.estPayante = estPayante;
        this.apportezBoissonsAperitifs = apportezBoissonsAperitifs;
    }

    @PrePersist
    protected void onCreate() {
        if (datePublication == null) {
            datePublication = new Date();
        }
        if(nbPlacesRestantes == null) {
            nbPlacesRestantes = 99999;
        }
    }

    public void addParticipation(Participation participation) {
        this.participations.add(participation);
        participation.setSoiree(this);
        this.nbPlacesRestantes--;
    }

    public void removeParticipation(Participation participation) {
        this.participations.remove(participation);
        participation.setSoiree(null);
        this.nbPlacesRestantes++;
    }

    public void addAvis(Avi avis) {
        this.avis.add(avis);
        avis.setSoiree(this);
    }

    public void removeAvis(Avi avis) {
        this.avis.remove(avis);
        avis.setSoiree(null);
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
        this.estPayante = (prix != null && prix.compareTo(BigDecimal.ZERO) > 0);
    }
}