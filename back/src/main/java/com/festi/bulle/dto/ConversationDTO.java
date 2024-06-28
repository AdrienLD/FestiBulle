package com.festi.bulle.dto;

import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
public class ConversationDTO {
    private Integer id;
    private Instant dateCreation;
    private Integer soireeId;
    private Boolean estGroupe;
    private List<Integer> utilisateurIds;
    private List<MessageDTO> messages;
}