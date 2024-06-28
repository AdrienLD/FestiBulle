package com.festi.bulle.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RechercheDTO {
    private String nom;
    private Integer adresseId;
    private String typeSoiree;
    private Integer nbPersonnes;
    private Boolean estPayante;
}