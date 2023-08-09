package com.example.venly.service.mapper;

import com.example.venly.service.dto.WordAssociationDto;
import com.example.venly.repository.model.WordAssociation;
public class WordAssociationMapper {

    public static WordAssociationDto toDto(WordAssociation wordAssociation) {
        WordAssociationDto dto = new WordAssociationDto();
        dto.setFirstWord(wordAssociation.getFirstWord());
        dto.setSecondWord(wordAssociation.getSecondWord());
        dto.setRelation(wordAssociation.getRelation());
        return dto;
    }

    public static WordAssociation toModel(WordAssociationDto wordAssociationDto) {
        WordAssociation model = new WordAssociation();
        model.setFirstWord(wordAssociationDto.getFirstWord());
        model.setSecondWord(wordAssociationDto.getSecondWord());
        model.setRelation(wordAssociationDto.getRelation());
        return model;
    }
}
