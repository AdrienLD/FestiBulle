package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "soireejeuxvideo")
public class Soireejeuxvideo {
    @Id
    @Column(name = "soiree_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "soiree_id", nullable = false)
    private Soiree soiree;

    @Size(max = 100)
    @NotNull
    @Column(name = "plateforme", nullable = false, length = 100)
    private String plateforme;

    @Column(name = "jeux_proposes")
    private String jeuxProposes;

    @Column(name = "nb_pc_disponibles")
    private int nbPcDisponibles;

    public Soireejeuxvideo() {
        this.jeuxProposes = "";
    }

    public Soireejeuxvideo(Soiree soiree, String plateforme) {
        this();
        this.soiree = soiree;
        this.id = soiree.getId();
        this.plateforme = plateforme;
    }

    public Soireejeuxvideo(Soiree soiree, String plateforme, String jeuxProposes, Integer nbPcDisponibles) {
        this(soiree, plateforme);
        this.jeuxProposes = jeuxProposes;
        this.nbPcDisponibles = nbPcDisponibles;
    }

    public void setSoiree(Soiree soiree) {
        this.soiree = soiree;
        this.id = soiree.getId();
    }

    public void removeSoiree() {
        if (this.soiree != null) {
            this.soiree.setSoireejeuxvideo(null);
            this.soiree = null;
            this.id = null;
        }
    }

    public void addJeu(String jeu) {
        this.jeuxProposes = jeu;
    }

    public void incrementNbPcDisponibles() {
        this.nbPcDisponibles++;
    }

    public void decrementNbPcDisponibles() {
        if (this.nbPcDisponibles > 0) {
            this.nbPcDisponibles--;
        }
    }
}