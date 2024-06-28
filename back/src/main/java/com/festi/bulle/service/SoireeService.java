package com.festi.bulle.service;

import com.festi.bulle.dto.RechercheDTO;
import com.festi.bulle.dto.SoireeDTO;
import com.festi.bulle.entity.Soiree;
import com.festi.bulle.entity.Utilisateur;
import com.festi.bulle.mapper.SoireeMapper;
import com.festi.bulle.repository.SoireeRepository;
import com.festi.bulle.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoireeService {

    private final SoireeRepository soireeRepository;
    private final SoireeMapper soireeMapper;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public SoireeService(SoireeRepository soireeRepository, SoireeMapper soireeMapper, UtilisateurRepository utilisateurRepository) {
        this.soireeRepository = soireeRepository;
        this.soireeMapper = soireeMapper;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Caching(
            put = @CachePut(value = "soirees", key = "#result.id"),
            evict = {
                    @CacheEvict(value = "rechercheSoirees", allEntries = true),
                    @CacheEvict(value = "allSoirees", allEntries = true)
            }
    )
    @Transactional
    public SoireeDTO createSoiree(SoireeDTO soireeDTO) {
        Soiree soiree = soireeMapper.toEntity(soireeDTO);
        Soiree savedSoiree = soireeRepository.save(soiree);
        return soireeMapper.toDTO(savedSoiree);
    }

    @Cacheable(value = "soirees", key = "#id")
    @Transactional(readOnly = true)
    public SoireeDTO getSoireeById(Integer id) {
        Soiree soiree = soireeRepository.findByIdWithDetails(id);
        return soireeMapper.toDTO(soiree);
    }

    @Cacheable(value = "allSoirees", key = "#pageable.pageNumber + '_' + #pageable.pageSize")
    @Transactional(readOnly = true)
    public List<SoireeDTO> getAllSoirees(Pageable pageable) {
        return soireeRepository.findAll(pageable).stream().map(soireeMapper::toDTO).toList();
    }

    @Caching(
            put = @CachePut(value = "soirees", key = "#id"),
            evict = {
                    @CacheEvict(value = "rechercheSoirees", allEntries = true),
                    @CacheEvict(value = "allSoirees", allEntries = true)
            }
    )
    @Transactional
    public SoireeDTO updateSoiree(Integer id, SoireeDTO soireeDTO, Integer userId) {
        soireeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soirée non trouvée"));
        Utilisateur organisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Soiree soiree = soireeMapper.toEntity(soireeDTO);
        soiree.setId(id);
        soiree.setOrganisateur(organisateur);
        return soireeMapper.toDTO(soireeRepository.save(soiree));
    }

    @Caching(evict = {
            @CacheEvict(value = "soirees", key = "#id"),
            @CacheEvict(value = "rechercheSoirees", allEntries = true),
            @CacheEvict(value = "allSoirees", allEntries = true)
    })
    @Transactional
    public void deleteSoiree(Integer id) {
        soireeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<SoireeDTO> getSoireesOrganisees(Integer organisateurId) {
        return soireeRepository.findByOrganisateurId(organisateurId)
                .stream()
                .map(soireeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SoireeDTO> getSoireesParticipees(Integer participantId) {
        return soireeRepository.findByParticipantId(participantId)
                .stream()
                .map(soireeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "rechercheSoirees", key = "{#rechercheDTO.adresseId, #rechercheDTO.typeSoiree, #rechercheDTO.estPayante}")
    @Transactional(readOnly = true)
    public List<SoireeDTO> rechercherSoirees(RechercheDTO rechercheDTO) {
        Date currentDate = new Date();

        List<Soiree> soirees = soireeRepository.rechercherSoirees(
                rechercheDTO.getAdresseId(),
                rechercheDTO.getTypeSoiree(),
                rechercheDTO.getNom(),
                rechercheDTO.getNbPersonnes(),
                rechercheDTO.getEstPayante(),
                currentDate
        );
        return soirees.stream().map(soireeMapper::toDTO).collect(Collectors.toList());
    }
}