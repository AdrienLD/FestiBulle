package com.festi.bulle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "soireejeuxsociete")
public class Soireejeuxsociete {
    @Id
    @Column(name = "soiree_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "soiree_id", nullable = false)
    private Soiree soiree;

    @Column(name = "jeux_proposes")
    @ElementCollection
    private List<String> jeuxProposes;

    public Soireejeuxsociete() {
        this.jeuxProposes = new ArrayList<>();
    }

    public Soireejeuxsociete(Soiree soiree) {
        this();
        this.soiree = soiree;
        this.id = soiree.getId();
    }

    public Soireejeuxsociete(Soiree soiree, List<String> jeuxProposes) {
        this(soiree);
        this.jeuxProposes = new ArrayList<>(jeuxProposes);
    }

    // Méthode pour associer une Soiree
    public void setSoiree(Soiree soiree) {
        this.soiree = soiree;
        this.id = soiree.getId();
    }

    public void removeSoiree() {
        if (this.soiree != null) {
            this.soiree.setSoireejeuxsociete(null);
            this.soiree = null;
            this.id = null;
        }
    }

    public void addJeu(String jeu) {
        if (this.jeuxProposes == null) {
            this.jeuxProposes = new ArrayList<>();
        }
        this.jeuxProposes.add(jeu);
    }

    public void removeJeu(String jeu) {
        if (this.jeuxProposes != null) {
            this.jeuxProposes.remove(jeu);
        }
    }

    public void setJeuxProposes(List<String> jeuxProposes) {
        this.jeuxProposes = new ArrayList<>(jeuxProposes);
    }
}