package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechercheDTO {
    private String nom;
    private Integer adresseId;
    private String typeSoiree;
    private Integer nbPersonnes;
    private Boolean estPayante;
}