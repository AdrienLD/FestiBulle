package com.festi.bulle.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class SoireeDTO {
    private Integer id;
    private String nom;
    private String description;
    private Instant dateHeure;
    private Integer adresseId;
    private Integer nbPlacesTotal;
    private Integer nbPlacesRestantes;
    private Boolean estPayante;
    private BigDecimal prix;
    private Instant datePublication;
    private Boolean apportezBoissonsAperitifs;
    private Integer organisateurId;
    private String typeSoiree;
}