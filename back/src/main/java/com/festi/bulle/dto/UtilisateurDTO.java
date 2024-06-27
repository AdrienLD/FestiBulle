package com.festi.bulle.dto;

import com.festi.bulle.entity.Adresse;
import com.festi.bulle.entity.Soiree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO {
    private Integer id;
    private String email;
    private String nom;
    private String prenom;
    private String motDePasse;
    private LocalDate dateNaissance;
    private AdresseDTO adresse;
    private String centresInterets;
    private Double noteMoyenne;
}