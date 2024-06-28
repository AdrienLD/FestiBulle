package com.festi.bulle.repository;

import com.festi.bulle.entity.Soiree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SoireeRepository extends JpaRepository<Soiree, Integer> {

    @Query("SELECT s FROM Soiree s WHERE s.organisateur.id = :organisateurId")
    List<Soiree> findByOrganisateurId(@Param("organisateurId") Integer organisateurId);

    @Query("SELECT s FROM Soiree s JOIN s.participations p WHERE p.utilisateur.id = :participantId")
    List<Soiree> findByParticipantId(@Param("participantId") Integer participantId);

    @Query("SELECT s FROM Soiree s LEFT JOIN FETCH s.adresse LEFT JOIN FETCH s.organisateur WHERE s.id = :id")
    Soiree findByIdWithDetails(@Param("id") Integer id);

    @Query("SELECT s FROM Soiree s WHERE " +
            "(:adresseId IS NULL OR s.adresse.id = :adresseId) AND " +
            "(:typeSoiree IS NULL OR s.typeSoiree = :typeSoiree) AND " +
            "(:nbPersonnes IS NULL OR :nbPersonnes >= s.nbPlacesRestantes) AND " +
            "(:nom IS NULL OR s.nom >= :nom) AND " +
            "(:estPayante IS NULL OR s.estPayante = :estPayante) AND " +
            "s.dateHeure > :currentDate")
    List<Soiree> rechercherSoirees(
            @Param("adresseId") Integer adresseId,
            @Param("typeSoiree") String typeSoiree,
            @Param("nom") String nom,
            @Param("nbPersonnes") Integer nbPersonnes,
            @Param("estPayante") Boolean estPayante,
            @Param("currentDate") Date currentDate
    );
}