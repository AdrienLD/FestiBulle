package com.festi.bulle.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class MessageDTO {
    private Integer id;
    private String contenu;
    private Instant dateEnvoi;
    private Integer utilisateurId;
    private String utilisateurNom;
    private Integer conversationId;
}