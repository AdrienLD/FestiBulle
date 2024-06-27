package com.festi.bulle.controller;

import com.festi.bulle.dto.SoireeDTO;
import com.festi.bulle.service.SoireeService;
import com.festi.bulle.service.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soirees")
@Tag(name = "Soirée", description = "API de gestion des soirées")
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
public class SoireeController {

    private final SoireeService soireeService;
    private final JWTService jwtService;

    @Autowired
    public SoireeController(SoireeService soireeService, JWTService jwtService) {
        this.soireeService = soireeService;
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

    @PostMapping
    @Operation(summary = "Créer une nouvelle soirée")
    public ResponseEntity<SoireeDTO> creerSoiree(@RequestBody SoireeDTO soireeDTO, @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        Integer userId = getUserIdFromToken(token);
        soireeDTO.setOrganisateurId(userId);
        SoireeDTO nouveauSoiree = soireeService.createSoiree(soireeDTO);
        return new ResponseEntity<>(nouveauSoiree, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Récupérer la liste des soirées")
    public ResponseEntity<List<SoireeDTO>> listerSoirees(Pageable pageable) {
        List<SoireeDTO> soirees = soireeService.getAllSoirees(pageable);
        return ResponseEntity.ok(soirees);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer les détails d'une soirée spécifique")
    public ResponseEntity<SoireeDTO> obtenirSoiree(
            @Parameter(description = "ID de la soirée") @PathVariable Integer id) {
        SoireeDTO soiree = soireeService.getSoireeById(id);
        return ResponseEntity.ok(soiree);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une soirée")
    public ResponseEntity<SoireeDTO> modifierSoiree(
            @Parameter(description = "ID de la soirée") @PathVariable Integer id,
            @RequestBody SoireeDTO soireeDTO,
            @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        Integer userId = getUserIdFromToken(token);
        SoireeDTO existingSoiree = soireeService.getSoireeById(id);
        if (!existingSoiree.getOrganisateurId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        SoireeDTO soireeModifiee = soireeService.updateSoiree(id, soireeDTO, userId);
        return ResponseEntity.ok(soireeModifiee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une soirée")
    public ResponseEntity<Void> supprimerSoiree(
            @Parameter(description = "ID de la soirée") @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        Integer userId = getUserIdFromToken(token);
        SoireeDTO existingSoiree = soireeService.getSoireeById(id);
        if (!existingSoiree.getOrganisateurId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        soireeService.deleteSoiree(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/participants")
    @Operation(summary = "Liste des participants à une soirée")
    public ResponseEntity<Integer> nombreParticipants(
            @Parameter(description = "ID de la soirée") @PathVariable Integer id) {
        SoireeDTO soiree = soireeService.getSoireeById(id);
        int nombreParticipants = soiree.getNbPlacesTotal() - soiree.getNbPlacesRestantes();
        return ResponseEntity.ok(nombreParticipants);
    }

    @GetMapping("/mes-soirees")
    @Operation(summary = "Liste des soirées organisées par l'utilisateur connecté")
    public ResponseEntity<List<SoireeDTO>> listerMesSoirees(@RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        Integer organisateurId = getUserIdFromToken(token);
        List<SoireeDTO> mesSoirees = soireeService.getSoireesOrganisees(organisateurId);
        return ResponseEntity.ok(mesSoirees);
    }

    @GetMapping("/mes-participations")
    @Operation(summary = "Liste des soirées auxquelles l'utilisateur participe")
    public ResponseEntity<List<SoireeDTO>> listerMesParticipations(@RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        Integer participantId = getUserIdFromToken(token);
        List<SoireeDTO> mesParticipations = soireeService.getSoireesParticipees(participantId);
        return ResponseEntity.ok(mesParticipations);
    }
}