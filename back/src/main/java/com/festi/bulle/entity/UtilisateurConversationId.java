package com.festi.bulle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UtilisateurConversationId implements java.io.Serializable {
    private static final long serialVersionUID = 6861529373971761783L;
    @NotNull
    @Column(name = "utilisateur_id", nullable = false)
    private Integer utilisateurId;

    @NotNull
    @Column(name = "conversation_id", nullable = false)
    private Integer conversationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UtilisateurConversationId entity = (UtilisateurConversationId) o;
        return Objects.equals(this.conversationId, entity.conversationId) &&
                Objects.equals(this.utilisateurId, entity.utilisateurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, utilisateurId);
    }

}