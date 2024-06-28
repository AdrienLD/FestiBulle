package com.festi.bulle.controller;

import com.festi.bulle.dto.ConversationDTO;
import com.festi.bulle.dto.MessageDTO;
import com.festi.bulle.entity.Message;
import com.festi.bulle.mapper.MessageMapper;
import com.festi.bulle.service.ConversationService;
import com.festi.bulle.service.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Conversation", description = "API de gestion des conversations")
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
public class ConversationController {

    private final ConversationService conversationService;
    private final JWTService jwtService;
    private final MessageMapper messageMapper;

    @Autowired
    public ConversationController(ConversationService conversationService, JWTService jwtService, MessageMapper messageMapper) {
        this.conversationService = conversationService;
        this.jwtService = jwtService;
        this.messageMapper = messageMapper;
    }

    private Integer getUserIdFromToken(String token) {
        String userId = jwtService.extractUsername(token);
        return Integer.parseInt(userId);
    }

    @GetMapping("/conversations")
    @Operation(summary = "Liste des conversations de l'utilisateur")
    public ResponseEntity<List<ConversationDTO>> getConversations(@RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader.substring(7));
        List<ConversationDTO> conversations = conversationService.getConversationsForUser(userId);
        return ResponseEntity.ok(conversations);
    }

    @GetMapping("/conversations/{id}")
    @Operation(summary = "Récupération des messages d'une conversation")
    public ResponseEntity<ConversationDTO> getConversation(@PathVariable Integer id) {
        ConversationDTO conversation = conversationService.getConversationById(id);
        return ResponseEntity.ok(conversation);
    }

    @PostMapping("/conversations/{id}/messages")
    @Operation(summary = "Envoi d'un message dans une conversation")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable Integer id, @RequestBody String content, @RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader.substring(7));
        Message message = conversationService.sendMessage(id, userId, content);
        return ResponseEntity.ok(messageMapper.toDTO(message));
    }

    @PostMapping("/conversations")
    @Operation(summary = "Création d'une nouvelle conversation (pour les conversations 1-1)")
    public ResponseEntity<ConversationDTO> createOneToOneConversation(@RequestParam Integer otherUserId, @RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader.substring(7));
        ConversationDTO conversation = conversationService.createOneToOneConversation(userId, otherUserId);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/soirees/{id}/conversation")
    @Operation(summary = "Récupération de la conversation de groupe d'une soirée")
    public ResponseEntity<ConversationDTO> getGroupConversationForSoiree(@PathVariable Integer id) {
        ConversationDTO conversation = conversationService.getGroupConversationForSoiree(id);
        return ResponseEntity.ok(conversation);
    }
}