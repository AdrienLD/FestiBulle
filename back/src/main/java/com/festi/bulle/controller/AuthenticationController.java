package com.festi.bulle.controller;

import com.festi.bulle.dto.LoginRequest;
import com.festi.bulle.dto.RegisterRequest;
import com.festi.bulle.dto.UtilisateurDTO;
import com.festi.bulle.service.UtilisateurService;
import com.festi.bulle.service.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final UtilisateurService utilisateurService;
    private final JWTService jwtService;

    public AuthenticationController(UtilisateurService utilisateurService, JWTService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        UtilisateurDTO utilisateur = utilisateurService.createUtilisateur(request);
        String token = jwtService.generateToken(utilisateur.getId().toString());
        Map<String, Object> response = new HashMap<>();
        response.put("utilisateur", utilisateur);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UtilisateurDTO utilisateur = utilisateurService.loginUtilisateur(request);
        String token = jwtService.generateToken(utilisateur.getId().toString());
        Map<String, Object> response = new HashMap<>();
        response.put("utilisateur", utilisateur);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}