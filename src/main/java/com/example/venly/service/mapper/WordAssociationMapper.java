package com.example.venly.service.mapper;

import com.example.venly.service.dto.RelationStatus;
import com.example.venly.service.dto.WordAssociationDto;
import com.example.venly.repository.model.WordAssociation;
import java.util.List;
import java.util.stream.Collectors;

public class WordAssociationMapper {

    public static WordAssociationDto toDto(WordAssociation wordAssociation) {
        WordAssociationDto dto = new WordAssociationDto();
        dto.setFirstWord(wordAssociation.getFirstWord());
        dto.setSecondWord(wordAssociation.getSecondWord());
        dto.setRelation(wordAssociation.getRelation());
        dto.setRelationStatus(RelationStatus.YES);
        return dto;
    }

    public static List<WordAssociationDto> toDtoList(List<WordAssociation> wordAssociations) {
        return wordAssociations.stream().map(WordAssociationMapper::toDto).collect(Collectors.toList());
    }

    public static WordAssociation toModel(WordAssociationDto wordAssociationDto) {
        WordAssociation model = new WordAssociation();
        model.setFirstWord(wordAssociationDto.getFirstWord().trim().toLowerCase());
        model.setSecondWord(wordAssociationDto.getSecondWord().trim().toLowerCase());
        model.setRelation(wordAssociationDto.getRelation());
        return model;
    }
}
