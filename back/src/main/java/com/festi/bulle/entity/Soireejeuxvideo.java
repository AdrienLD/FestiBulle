package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @ElementCollection
    private List<String> jeuxProposes;

    @Column(name = "nb_pc_disponibles")
    private Integer nbPcDisponibles;

}