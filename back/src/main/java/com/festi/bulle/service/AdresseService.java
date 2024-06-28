package com.festi.bulle.service;

import com.festi.bulle.dto.AdresseDTO;
import com.festi.bulle.entity.Adresse;
import com.festi.bulle.mapper.AdresseMapper;
import com.festi.bulle.repository.AdresseRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdresseService {

    private final AdresseRepository adresseRepository;
    private final AdresseMapper adresseMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "adresses", key = "#id")
    public AdresseDTO getAdresse(Integer id) {
        return adresseRepository.findById(id)
                .map(adresseMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Adresse non trouvée"));
    }

    @Transactional(readOnly = true)
    public Page<AdresseDTO> getAllAdresses(Pageable pageable) {
        return adresseRepository.findAll(pageable)
                .map(adresseMapper::toDTO);
    }

    @Transactional
    public AdresseDTO createAdresse(AdresseDTO adresseDTO) {
        Adresse adresse = adresseMapper.toEntity(adresseDTO);
        return adresseMapper.toDTO(adresseRepository.save(adresse));
    }

    @Transactional(readOnly = true)
    public List<AdresseDTO> getAdressesByVille(String ville) {
        return adresseRepository.findByVille(ville).stream()
                .map(adresseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AdresseDTO> getAdressesByRegion(String region) {
        return adresseRepository.findByRegion(region).stream()
                .map(adresseMapper::toDTO)
                .collect(Collectors.toList());
    }
}