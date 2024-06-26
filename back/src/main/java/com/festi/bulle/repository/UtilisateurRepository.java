package com.festi.bulle.repository;

import com.festi.bulle.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);

    @Query("SELECT u FROM Utilisateur u LEFT JOIN FETCH u.adresse WHERE u.id = :id")
    Optional<Utilisateur> findByIdWithAdresse(Integer id);
}