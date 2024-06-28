package com.festi.bulle.dto;

import lombok.Data;

@Data
public class AviDTO {
    private Integer id;
    private String commentaire;
    private int note;
    private UtilisateurDTO utilisateur;
    private SoireeDTO soiree;
    private UtilisateurDTO toUserId;
}