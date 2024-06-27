package com.festi.bulle.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SoireeDTO {
    private Integer id;
    private String nom;
    private String description;
    private Date dateHeure;
    private Integer adresseId;
    private int nbPlacesTotal;
    private int nbPlacesRestantes;
    private Boolean estPayante;
    private BigDecimal prix;
    private Date datePublication;
    private Boolean apportezBoissonsAperitifs;
    private Integer organisateurId;
    private String typeSoiree;
}