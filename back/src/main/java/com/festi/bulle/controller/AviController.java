package com.festi.bulle.controller;

import com.festi.bulle.dto.AviDTO;
import com.festi.bulle.service.AviService;
import com.festi.bulle.service.JWTService;
import com.festi.bulle.utils.Errors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
@Tag(name = "Avis", description = "API de gestion des avis")
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
public class AviController {

    private final AviService aviService;
    private final JWTService jwtService;

    @Autowired
    public AviController(AviService aviService, JWTService jwtService) {
        this.aviService = aviService;
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

    @PostMapping("/utilisateurs/{id}")
    @Operation(summary = "Ajouter un avis sur un utilisateur")
    public ResponseEntity<?> addAviToUtilisateur(
            @Parameter(description = "ID de l'utilisateur cible") @PathVariable Integer id,
            @RequestBody AviDTO aviDTO,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = extractToken(authHeader);
            Integer userId = getUserIdFromToken(token);
            AviDTO createdAvi = aviService.addAviToUtilisateur(id, aviDTO, userId);
            return ResponseEntity.ok(createdAvi);
        } catch (Exception e) {
            return Errors.badRequest(e);
        }
    }

    @PostMapping("/soirees/{id}")
    @Operation(summary = "Ajouter un avis sur une soirée")
    public ResponseEntity<?> addAviToSoiree(
            @Parameter(description = "ID de la soirée") @PathVariable Integer id,
            @RequestBody AviDTO aviDTO,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = extractToken(authHeader);
            Integer userId = getUserIdFromToken(token);
            AviDTO createdAvi = aviService.addAviToSoiree(id, aviDTO, userId);
            return ResponseEntity.ok(createdAvi);
        } catch (Exception e) {
            return Errors.badRequest(e);
        }
    }

    @GetMapping("/soirees/{id}")
    @Operation(summary = "Récupérer les avis d'une soirée")
    public ResponseEntity<?> getAvisBySoireeId(
            @Parameter(description = "ID de la soirée") @PathVariable Integer id) {
        try {
            List<AviDTO> avis = aviService.getAvisBySoireeId(id);
            return ResponseEntity.ok(avis);
        } catch (Exception e) {
            return Errors.badRequest(e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier son avis")
    public ResponseEntity<?> updateAvi(
            @Parameter(description = "ID de l'avis") @PathVariable Integer id,
            @RequestBody AviDTO aviDTO,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = extractToken(authHeader);
            Integer userId = getUserIdFromToken(token);
            AviDTO updatedAvi = aviService.updateAvi(id, aviDTO, userId);
            return ResponseEntity.ok(updatedAvi);
        } catch (Exception e) {
            return Errors.badRequest(e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer son avis")
    public ResponseEntity<?> deleteAvi(
            @Parameter(description = "ID de l'avis") @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = extractToken(authHeader);
            Integer userId = getUserIdFromToken(token);
            aviService.deleteAvi(id, userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return Errors.badRequest(e);
        }
    }
}