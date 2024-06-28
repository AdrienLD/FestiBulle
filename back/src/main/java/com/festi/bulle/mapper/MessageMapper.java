package com.festi.bulle.mapper;

import com.festi.bulle.dto.MessageDTO;
import com.festi.bulle.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "utilisateurId", source = "utilisateur.id")
    @Mapping(target = "utilisateurNom", source = "utilisateur.nom")
    @Mapping(target = "conversationId", source = "conversation.id")
    MessageDTO toDTO(Message message);

    @Mapping(target = "utilisateur.id", source = "utilisateurId")
    @Mapping(target = "conversation.id", source = "conversationId")
    Message toEntity(MessageDTO messageDTO);
}