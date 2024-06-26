package com.festi.bulle.repository;

import com.festi.bulle.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
    List<Adresse> findByVille(String ville);
    List<Adresse> findByRegion(String region);
}