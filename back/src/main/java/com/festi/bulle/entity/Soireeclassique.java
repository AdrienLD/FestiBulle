package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "soireeclassique")
public class Soireeclassique {
    @Id
    @Column(name = "soiree_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "soiree_id", nullable = false)
    private Soiree soiree;

    @Size(max = 100)
    @Column(name = "theme", length = 100)
    private String theme;

    public Soireeclassique() {
    }

    public Soireeclassique(Soiree soiree) {
        this.soiree = soiree;
        this.id = soiree.getId();
    }

    public Soireeclassique(Soiree soiree, String theme) {
        this(soiree);
        this.theme = theme;
    }

    public void setSoiree(Soiree soiree) {
        this.soiree = soiree;
        this.id = soiree.getId();
    }

    public void removeSoiree() {
        if (this.soiree != null) {
            this.soiree.setSoireeclassique(null);
            this.soiree = null;
            this.id = null;
        }
    }
}