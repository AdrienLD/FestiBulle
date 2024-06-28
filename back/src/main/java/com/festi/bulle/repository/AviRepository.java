package com.festi.bulle.repository;

import com.festi.bulle.entity.Avi;
import com.festi.bulle.entity.Soiree;
import com.festi.bulle.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AviRepository extends JpaRepository<Avi, Integer> {
    List<Avi> findBySoireeId(Integer soireeId);
    boolean existsByUtilisateurAndSoiree(Utilisateur utilisateur, Soiree soiree);
}