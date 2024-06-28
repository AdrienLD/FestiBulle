package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdresseDTO {
    private Integer id;
    private String nomLieu;
    private String libelle;
    private String ville;
    private String region;
    private String codePostal;
}