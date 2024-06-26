package com.festi.bulle.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
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

    public UtilisateurConversation() {
        this.id = new UtilisateurConversationId();
    }

    public UtilisateurConversation(Utilisateur utilisateur, Conversation conversation) {
        this.id = new UtilisateurConversationId(utilisateur.getId(), conversation.getId());
        this.utilisateur = utilisateur;
        this.conversation = conversation;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.id.setUtilisateurId(utilisateur.getId());
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
        this.id.setConversationId(conversation.getId());
    }

    public void addToUtilisateurAndConversation() {
        utilisateur.getConversations().add(conversation);
        conversation.getUtilisateurs().add(utilisateur);
    }

    public void removeFromUtilisateurAndConversation() {
        utilisateur.getConversations().remove(conversation);
        conversation.getUtilisateurs().remove(utilisateur);
    }
}