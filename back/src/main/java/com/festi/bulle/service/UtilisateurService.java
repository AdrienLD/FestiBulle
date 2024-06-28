package com.festi.bulle.service;

import com.festi.bulle.dto.LoginRequest;
import com.festi.bulle.dto.RegisterRequest;
import com.festi.bulle.dto.UtilisateurDTO;
import com.festi.bulle.entity.Utilisateur;
import com.festi.bulle.mapper.UtilisateurMapper;
import com.festi.bulle.repository.UtilisateurRepository;
import com.festi.bulle.utils.Functions;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "utilisateurs", key = "#id")
    public UtilisateurDTO getUtilisateur(Integer id) {
        return utilisateurRepository.findByIdWithAdresse(id)
                .map(utilisateurMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Transactional(readOnly = true)
    public Page<UtilisateurDTO> getAllUtilisateurs(Pageable pageable) {
        return utilisateurRepository.findAll(pageable)
                .map(utilisateurMapper::toDTO);
    }

    @Transactional
    public UtilisateurDTO createUtilisateur(RegisterRequest registerRequest) {
        if (utilisateurRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        if (!Functions.isValidEmail(registerRequest.getEmail())){
            throw new RuntimeException("Email invalide");
        }

        if (!Functions.isPassWordValid(registerRequest.getMotDePasse())){
            throw new RuntimeException("La mot de passe doit faire au moins 6 caractères");
        }

        String email = registerRequest.getEmail();
        String motDePasse = BCrypt.hashpw(registerRequest.getMotDePasse(), BCrypt.gensalt());
        String nom = registerRequest.getNom();
        Utilisateur utilisateur = new Utilisateur(email, motDePasse, nom);

        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDTO(utilisateur);
    }

    @Transactional
    public UtilisateurDTO loginUtilisateur(LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        if (!BCrypt.checkpw(loginRequest.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        return utilisateurMapper.toDTO(utilisateur);
    }

    @Transactional
    public UtilisateurDTO updateUtilisateur(Integer id, UtilisateurDTO utilisateurDTO) {
        utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Utilisateur user = utilisateurMapper.toEntity(utilisateurDTO);
        user.setId(id);
        return utilisateurMapper.toDTO(utilisateurRepository.save(user));
    }

    @Transactional
    public void deleteUtilisateur(Integer id) {
        utilisateurRepository.deleteById(id);
    }

   /* @Transactional(readOnly = true)
    public Page<AvisDTO> getAvisUtilisateur(Integer id, Pageable pageable) {
        return avisRepository.findByUtilisateurId(id, pageable)
                .map(avisMapper::toDTO);
    } */
}