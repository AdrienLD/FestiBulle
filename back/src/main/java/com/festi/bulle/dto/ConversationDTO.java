package com.festi.bulle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {
    private Integer id;
    private Instant dateCreation;
    private Integer soireeId;
    private Boolean estGroupe;
    private List<Integer> utilisateurIds;
    private List<MessageDTO> messages;
}