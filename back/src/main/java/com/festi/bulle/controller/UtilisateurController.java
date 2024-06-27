package com.festi.bulle.controller;

import com.festi.bulle.dto.LoginRequest;
import com.festi.bulle.dto.RegisterRequest;
import com.festi.bulle.dto.UtilisateurDTO;
import com.festi.bulle.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@Tag(name = "Utilisateur", description = "API de gestion des utilisateurs")
@CrossOrigin(origins = "http://localhost:3000")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par son ID")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable Integer id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateur(id));
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs avec pagination")
    public ResponseEntity<Page<UtilisateurDTO>> getAllUtilisateurs(Pageable pageable) {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs(pageable));
    }

    @PostMapping("/register")
    @Operation(summary = "Inscription d'un nouvel utilisateur")
    public ResponseEntity<UtilisateurDTO> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurService.createUtilisateur(registerRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion d'un utilisateur")
    public ResponseEntity<UtilisateurDTO> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(utilisateurService.loginUtilisateur(loginRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mise à jour du profil de l'utilisateur connecté")
    public ResponseEntity<UtilisateurDTO> updateProfil(@PathVariable Integer id, @RequestBody UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.updateUtilisateur(id,utilisateurDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Suppression du compte de l'utilisateur connecté")
    public ResponseEntity<Void> deleteProfil(@PathVariable Integer id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    /* @GetMapping("/{id}/avis")
    @Operation(summary = "Récupération des avis sur un utilisateur")
    public ResponseEntity<Page<AvisDTO>> getAvisUtilisateur(@PathVariable Integer id, Pageable pageable) {
        return ResponseEntity.ok(utilisateurService.getAvisUtilisateur(id, pageable));
    }*/

}