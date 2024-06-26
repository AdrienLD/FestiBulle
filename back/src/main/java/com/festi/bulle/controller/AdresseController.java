package com.festi.bulle.controller;

import com.festi.bulle.dto.AdresseDTO;
import com.festi.bulle.service.AdresseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adresses")
@Tag(name = "Adresse", description = "API de gestion des adresses")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une adresse par son ID")
    public ResponseEntity<AdresseDTO> getAdresse(@PathVariable Integer id) {
        return ResponseEntity.ok(adresseService.getAdresse(id));
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les adresses avec pagination")
    public ResponseEntity<Page<AdresseDTO>> getAllAdresses(Pageable pageable) {
        return ResponseEntity.ok(adresseService.getAllAdresses(pageable));
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle adresse")
    public ResponseEntity<AdresseDTO> createAdresse(@RequestBody AdresseDTO adresseDTO) {
        return ResponseEntity.ok(adresseService.createAdresse(adresseDTO));
    }

    @GetMapping("/ville/{ville}")
    @Operation(summary = "Récupérer les adresses par ville")
    public ResponseEntity<List<AdresseDTO>> getAdressesByVille(@PathVariable String ville) {
        return ResponseEntity.ok(adresseService.getAdressesByVille(ville));
    }

    @GetMapping("/region/{region}")
    @Operation(summary = "Récupérer les adresses par région")
    public ResponseEntity<List<AdresseDTO>> getAdressesByRegion(@PathVariable String region) {
        return ResponseEntity.ok(adresseService.getAdressesByRegion(region));
    }

    // Autres endpoints CRUD...
}