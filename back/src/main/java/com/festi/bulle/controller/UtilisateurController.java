package com.festi.bulle.controller;

import com.festi.bulle.dto.UtilisateurDTO;
import com.festi.bulle.service.JWTService;
import com.festi.bulle.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@Tag(name = "Utilisateur", description = "API de gestion des utilisateurs")
@CrossOrigin(origins = "http://localhost:3000")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final JWTService jwtService;

    public UtilisateurController(UtilisateurService utilisateurService, JWTService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Token JWT invalide ou manquant");
    }

    private Integer getUserIdFromToken(String token) {
        String userId = jwtService.extractUsername(token);
        return Integer.parseInt(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par son ID")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable Integer id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateur(id));
    }

    @GetMapping("/profile")
    @Operation(summary = "Récupérer un utilisateur par son ID")
    public ResponseEntity<UtilisateurDTO> getProfile(@RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        Integer id = getUserIdFromToken(token);
        return ResponseEntity.ok(utilisateurService.getUtilisateur(id));
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs avec pagination")
    public ResponseEntity<Page<UtilisateurDTO>> getAllUtilisateurs(Pageable pageable) {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs(pageable));
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