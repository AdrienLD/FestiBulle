package com.festi.bulle.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class AviDTO {
    private Integer id;
    private String commentaire;
    private Integer note;
    private Instant dateCreation;
    private Integer utilisateurId;
    private Integer soireeId;
}