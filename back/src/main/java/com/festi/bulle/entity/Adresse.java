package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresse_id_gen")
    @SequenceGenerator(name = "adresse_id_gen", sequenceName = "adresse_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "nom_lieu")
    private String nomLieu;

    @Size(max = 255)
    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Size(max = 100)
    @NotNull
    @Column(name = "ville", nullable = false, length = 100)
    private String ville;

    @Size(max = 100)
    @NotNull
    @Column(name = "region", nullable = false, length = 100)
    private String region;

    @Size(max = 20)
    @NotNull
    @Column(name = "code_postal", nullable = false, length = 20)
    private String codePostal;

    @OneToMany(mappedBy = "adresse")
    private Set<Soiree> soirees;

    @OneToMany(mappedBy = "adresse")
    private Set<Utilisateur> utilisateurs;

    public Adresse() {
        this.soirees = new LinkedHashSet<>();
        this.utilisateurs = new LinkedHashSet<>();
    }

    public Adresse(String libelle, String ville, String region, String codePostal) {
        this();
        this.libelle = libelle;
        this.ville = ville;
        this.region = region;
        this.codePostal = codePostal;
    }

    public Adresse(String nomLieu, String libelle, String ville, String region, String codePostal) {
        this(libelle, ville, region, codePostal);
        this.nomLieu = nomLieu;
    }
}