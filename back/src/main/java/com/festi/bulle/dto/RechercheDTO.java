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
    private String ville;
    private String typeSoiree;
    private Integer nbPersonnes;
    private Boolean estPayante;
}