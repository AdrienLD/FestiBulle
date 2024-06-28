package com.festi.bulle.service;

import com.festi.bulle.dto.ConversationDTO;
import com.festi.bulle.entity.Conversation;
import com.festi.bulle.entity.Message;
import com.festi.bulle.entity.Utilisateur;
import com.festi.bulle.mapper.ConversationMapper;
import com.festi.bulle.repository.ConversationRepository;
import com.festi.bulle.repository.MessageRepository;
import com.festi.bulle.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationMapper conversationMapper;
    private final UtilisateurRepository utilisateurRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, ConversationMapper conversationMapper,
                               UtilisateurRepository utilisateurRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.conversationMapper = conversationMapper;
        this.utilisateurRepository = utilisateurRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<ConversationDTO> getConversationsForUser(Integer userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return conversationRepository.findByUtilisateur(utilisateur).stream()
                .map(conversationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "conversations", key = "#id")
    @Transactional(readOnly = true)
    public ConversationDTO getConversationById(Integer id) {
        Conversation conversation = conversationRepository.findByIdWithUtilisateursAndMessages(id)
                .orElseThrow(() -> new RuntimeException("Conversation non trouvée"));

        // Assurez-vous que les utilisateurs et les messages sont chargés
        conversation.getUtilisateurs().size(); // Force le chargement des utilisateurs
        conversation.getMessages().forEach(message -> message.getUtilisateur().getNom()); // Force le chargement des noms d'utilisateurs pour les messages

        return conversationMapper.toDTO(conversation);
    }

    @Transactional
    public Message sendMessage(Integer conversationId, Integer userId, String content) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation non trouvée"));
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Message message = new Message(content, utilisateur, conversation);
        return messageRepository.save(message);
    }

    @Transactional
    public ConversationDTO createOneToOneConversation(Integer userId1, Integer userId2) {
        Utilisateur user1 = utilisateurRepository.findById(userId1)
                .orElseThrow(() -> new RuntimeException("Utilisateur 1 non trouvé"));
        Utilisateur user2 = utilisateurRepository.findById(userId2)
                .orElseThrow(() -> new RuntimeException("Utilisateur 2 non trouvé"));

        Conversation conversation = new Conversation();
        conversation.setEstGroupe(false);
        conversation.getUtilisateurs().add(user1);
        conversation.getUtilisateurs().add(user2);

        Conversation savedConversation = conversationRepository.save(conversation);
        return conversationMapper.toDTO(savedConversation);
    }

    @Transactional(readOnly = true)
    public ConversationDTO getGroupConversationForSoiree(Integer soireeId) {
        Conversation conversation = conversationRepository.findGroupConversationBySoireeId(soireeId)
                .orElseThrow(() -> new RuntimeException("Conversation de groupe non trouvée pour cette soirée"));
        return conversationMapper.toDTO(conversation);
    }
}