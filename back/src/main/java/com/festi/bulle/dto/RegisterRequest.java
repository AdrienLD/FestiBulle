package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String motDePasse;
    private String nom;
    // Ajoutez d'autres champs si nécessaire
}