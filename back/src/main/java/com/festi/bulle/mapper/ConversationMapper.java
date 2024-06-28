package com.festi.bulle.mapper;

import com.festi.bulle.dto.ConversationDTO;
import com.festi.bulle.entity.Conversation;
import com.festi.bulle.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface ConversationMapper {

    @Mapping(target = "soireeId", source = "soiree.id")
    @Mapping(target = "utilisateurIds", expression = "java(mapUtilisateurIds(conversation))")
    ConversationDTO toDTO(Conversation conversation);

    default List<Integer> mapUtilisateurIds(Conversation conversation) {
        return conversation.getUtilisateurs().stream()
                .map(Utilisateur::getId)
                .collect(Collectors.toList());
    }
}