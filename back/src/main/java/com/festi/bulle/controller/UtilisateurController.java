package com.festi.bulle.controller;

import com.festi.bulle.dto.RegisterRequest;
import com.festi.bulle.dto.UtilisateurDTO;
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
    // Autres endpoints CRUD...
}