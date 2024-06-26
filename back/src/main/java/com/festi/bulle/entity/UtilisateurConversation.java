package com.festi.bulle.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur_conversation")
public class UtilisateurConversation {
    @SequenceGenerator(name = "utilisateur_conversation_id_gen", sequenceName = "utilisateur_id_seq", allocationSize = 1)
    @EmbeddedId
    private UtilisateurConversationId id;

    @MapsId("utilisateurId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @MapsId("conversationId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

}