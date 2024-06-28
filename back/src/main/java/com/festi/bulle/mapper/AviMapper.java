package com.festi.bulle.mapper;

import com.festi.bulle.dto.AviDTO;
import com.festi.bulle.entity.Avi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, SoireeMapper.class})
public interface AviMapper {

    AviDTO toDTO(Avi avi);

    @Mapping(target = "soiree", ignore = true)
    @Mapping(target = "toUserId", ignore = true)
    Avi toEntity(AviDTO aviDTO);
}