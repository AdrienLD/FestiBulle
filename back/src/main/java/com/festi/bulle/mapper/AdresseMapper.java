package com.festi.bulle.mapper;

import com.festi.bulle.dto.AdresseDTO;
import com.festi.bulle.entity.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    AdresseDTO toDTO(Adresse adresse);

    Adresse toEntity(AdresseDTO adresseDTO);
}