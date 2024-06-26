package com.festi.bulle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participation")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participation_id_gen")
    @SequenceGenerator(name = "participation_id_gen", sequenceName = "participation_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "soiree_id", nullable = false)
    private Soiree soiree;

    @Size(max = 20)
    @NotNull
    @Column(name = "statut", nullable = false, length = 20)
    private String statut;

    @NotNull
    @Column(name = "date_demande", nullable = false)
    private Instant dateDemande;

    @Column(name = "date_reponse")
    private Instant dateReponse;

}