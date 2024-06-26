package com.festi.bulle.controller;

import com.festi.bulle.dto.LoginRequest;
import com.festi.bulle.dto.RegisterRequest;
import com.festi.bulle.dto.UtilisateurDTO;
import com.festi.bulle.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UtilisateurService utilisateurService;

    public AuthenticationController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/register")
    public ResponseEntity<UtilisateurDTO> register(@RequestBody RegisterRequest request) {
        UtilisateurDTO utilisateur = utilisateurService.createUtilisateur(request);
        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping("/login")
    public ResponseEntity<UtilisateurDTO> login(@RequestBody LoginRequest request) {
        UtilisateurDTO utilisateur = utilisateurService.loginUtilisateur(request);
        return ResponseEntity.ok(utilisateur);
    }
}