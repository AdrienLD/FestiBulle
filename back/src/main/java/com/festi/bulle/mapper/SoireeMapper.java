package com.festi.bulle.mapper;

import com.festi.bulle.dto.SoireeDTO;
import com.festi.bulle.entity.Soiree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SoireeMapper {

    @Mapping(target = "adresseId", source = "adresse.id")
    @Mapping(target = "organisateurId", source = "organisateur.id")
    SoireeDTO toDTO(Soiree soiree);

    @Mapping(target = "adresse.id", source = "adresseId")
    @Mapping(target = "organisateur.id", source = "organisateurId")
    @Mapping(target = "avis", ignore = true)
    @Mapping(target = "conversations", ignore = true)
    @Mapping(target = "participations", ignore = true)
    @Mapping(target = "soireeclassique", ignore = true)
    @Mapping(target = "soireejeuxsociete", ignore = true)
    @Mapping(target = "soireejeuxvideo", ignore = true)
    Soiree toEntity(SoireeDTO soireeDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "organisateur", ignore = true)
    @Mapping(target = "avis", ignore = true)
    @Mapping(target = "conversations", ignore = true)
    @Mapping(target = "participations", ignore = true)
    @Mapping(target = "soireeclassique", ignore = true)
    @Mapping(target = "soireejeuxsociete", ignore = true)
    @Mapping(target = "soireejeuxvideo", ignore = true)
    void updateSoireeFromDTO(SoireeDTO soireeDTO, @MappingTarget Soiree soiree);
}