package com.festi.bulle.service;

import com.festi.bulle.dto.SoireeDTO;
import com.festi.bulle.entity.Soiree;
import com.festi.bulle.mapper.SoireeMapper;
import com.festi.bulle.repository.SoireeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoireeService {

    private final SoireeRepository soireeRepository;
    private final SoireeMapper soireeMapper;

    @Autowired
    public SoireeService(SoireeRepository soireeRepository, SoireeMapper soireeMapper) {
        this.soireeRepository = soireeRepository;
        this.soireeMapper = soireeMapper;
    }

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

    @Transactional(readOnly = true)
    public List<SoireeDTO> getAllSoirees(Pageable pageable) {
        return soireeRepository.findAll(pageable).stream().map(soireeMapper::toDTO).toList();
    }

    @CacheEvict(value = "soirees", key = "#id")
    @Transactional
    public SoireeDTO updateSoiree(Integer id, SoireeDTO soireeDTO) {
        Soiree existingSoiree = soireeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soirée non trouvée"));
        soireeMapper.updateSoireeFromDTO(soireeDTO, existingSoiree);
        Soiree updatedSoiree = soireeRepository.save(existingSoiree);
        return soireeMapper.toDTO(updatedSoiree);
    }

    @CacheEvict(value = "soirees", key = "#id")
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
}