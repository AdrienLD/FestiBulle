package com.festi.bulle.repository;

import com.festi.bulle.entity.Soiree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoireeRepository extends JpaRepository<Soiree, Integer> {

    @Query("SELECT s FROM Soiree s WHERE s.organisateur.id = :organisateurId")
    List<Soiree> findByOrganisateurId(@Param("organisateurId") Integer organisateurId);

    @Query("SELECT s FROM Soiree s JOIN s.participations p WHERE p.utilisateur.id = :participantId")
    List<Soiree> findByParticipantId(@Param("participantId") Integer participantId);

    @Query("SELECT s FROM Soiree s LEFT JOIN FETCH s.adresse LEFT JOIN FETCH s.organisateur WHERE s.id = :id")
    Soiree findByIdWithDetails(@Param("id") Integer id);
}