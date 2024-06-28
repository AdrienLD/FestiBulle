package com.festi.bulle.repository;

import com.festi.bulle.entity.Conversation;
import com.festi.bulle.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    @Query("SELECT c FROM Conversation c LEFT JOIN FETCH c.utilisateurs WHERE c.id = :id")
    Optional<Conversation> findByIdWithUtilisateurs(@Param("id") Integer id);


    @Query("SELECT c FROM Conversation c WHERE :utilisateur MEMBER OF c.utilisateurs")
    List<Conversation> findByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);

    @Query("SELECT c FROM Conversation c WHERE c.soiree.id = :soireeId AND c.estGroupe = true")
    Optional<Conversation> findGroupConversationBySoireeId(@Param("soireeId") Integer soireeId);

    @Query("SELECT DISTINCT c FROM Conversation c " +
            "LEFT JOIN FETCH c.utilisateurs " +
            "LEFT JOIN FETCH c.messages m " +
            "LEFT JOIN FETCH m.utilisateur " +
            "WHERE c.id = :id")
    Optional<Conversation> findByIdWithUtilisateursAndMessages(@Param("id") Integer id);
}