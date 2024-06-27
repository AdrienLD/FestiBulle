package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO {
    private Integer id;
    private String email;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private AdresseDTO adresse;
    private String centresInterets;
    private Double noteMoyenne;
}