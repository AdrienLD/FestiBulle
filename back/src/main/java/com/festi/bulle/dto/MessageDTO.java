package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Integer id;
    private String contenu;
    private Instant dateEnvoi;
    private Integer utilisateurId;
    private String utilisateurNom;
    private Integer conversationId;
}