package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AviDTO {
    private Integer id;
    private String commentaire;
    private int note;
    private UtilisateurDTO utilisateur;
    private SoireeDTO soiree;
    private UtilisateurDTO toUserId;
}