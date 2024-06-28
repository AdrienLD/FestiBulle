package com.festi.bulle.service;

import com.festi.bulle.dto.AviDTO;
import com.festi.bulle.entity.Avi;
import com.festi.bulle.entity.Soiree;
import com.festi.bulle.entity.Utilisateur;
import com.festi.bulle.mapper.AviMapper;
import com.festi.bulle.repository.AviRepository;
import com.festi.bulle.repository.SoireeRepository;
import com.festi.bulle.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AviService {

    private final AviRepository aviRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final SoireeRepository soireeRepository;
    private final AviMapper aviMapper;

    @Autowired
    public AviService(AviRepository aviRepository, UtilisateurRepository utilisateurRepository,
                      SoireeRepository soireeRepository, AviMapper aviMapper) {
        this.aviRepository = aviRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.soireeRepository = soireeRepository;
        this.aviMapper = aviMapper;
    }

    @Transactional
    public AviDTO addAviToUtilisateur(Integer toUserId, AviDTO aviDTO, Integer userId) throws Exception {
        Utilisateur auteur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Utilisateur cible = utilisateurRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Utilisateur cible non trouvé"));

        if (auteur.getId().equals(cible.getId())) {
            throw new Exception("Vous ne pouvez pas donner un avis sur vous-même");
        }

        Avi avi = aviMapper.toEntity(aviDTO);
        avi.setUtilisateur(auteur);
        avi.setToUserId(cible);
        avi.setSoiree(null);

        return aviMapper.toDTO(aviRepository.save(avi));
    }

    @Transactional
    public AviDTO addAviToSoiree(Integer soireeId, AviDTO aviDTO, Integer userId) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Soiree soiree = soireeRepository.findById(soireeId)
                .orElseThrow(() -> new RuntimeException("Soirée non trouvée"));

        if (aviRepository.existsByUtilisateurAndSoiree(utilisateur, soiree)) {
            throw new Exception("Vous avez déjà donné un avis sur cette soirée");
        }

        Avi avi = aviMapper.toEntity(aviDTO);
        avi.setUtilisateur(utilisateur);
        avi.setSoiree(soiree);
        avi.setToUserId(null);

        return aviMapper.toDTO(aviRepository.save(avi));
    }

    public List<AviDTO> getAvisBySoireeId(Integer soireeId) {
        return aviRepository.findBySoireeId(soireeId).stream()
                .map(aviMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AviDTO updateAvi(Integer id, AviDTO aviDTO, Integer userId) throws Exception {
        Avi avi = aviRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé"));

        if (!avi.getUtilisateur().getId().equals(userId)) {
            throw new Exception("Vous n'êtes pas autorisé à modifier cet avis");
        }

        avi.setCommentaire(aviDTO.getCommentaire());
        avi.setNote(aviDTO.getNote());

        return aviMapper.toDTO(aviRepository.save(avi));
    }

    @Transactional
    public void deleteAvi(Integer id, Integer userId) throws Exception {
        Avi avi = aviRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé"));

        if (!avi.getUtilisateur().getId().equals(userId)) {
            throw new Exception("Vous n'êtes pas autorisé à supprimer cet avis");
        }

        aviRepository.deleteById(id);
    }
}