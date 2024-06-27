package com.festi.bulle.mapper;

import com.festi.bulle.dto.UtilisateurDTO;
import com.festi.bulle.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AdresseMapper.class)
public interface UtilisateurMapper {

    @Mapping(target = "motDePasse", ignore = true)
    UtilisateurDTO toDTO(Utilisateur utilisateur);

    @Mapping(target = "avis", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "participations", ignore = true)
    @Mapping(target = "soirees", ignore = true)
    @Mapping(target = "conversations", ignore = true)
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
}