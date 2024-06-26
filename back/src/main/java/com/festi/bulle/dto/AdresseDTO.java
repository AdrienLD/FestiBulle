package com.festi.bulle.dto;

import lombok.Data;

@Data
public class AdresseDTO {
    private Integer id;
    private String nomLieu;
    private String libelle;
    private String ville;
    private String region;
    private String codePostal;
}