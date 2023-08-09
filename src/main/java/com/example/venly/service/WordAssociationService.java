package com.example.venly.service;

import com.example.venly.repository.WordAssociationRepository;
import com.example.venly.repository.model.Relation;
import com.example.venly.service.dto.WordAssociationDto;
import com.example.venly.service.mapper.WordAssociationMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WordAssociationService {

    private final WordAssociationRepository wordAssociationRepository;

    public WordAssociationService(
            WordAssociationRepository wordAssociationRepository) {
        this.wordAssociationRepository = wordAssociationRepository;
    }

    public WordAssociationDto createWordAssociation(WordAssociationDto wordAssociationDto) {
        return WordAssociationMapper.toDto(wordAssociationRepository.save(WordAssociationMapper.toModel(wordAssociationDto)));
    }

    public List<WordAssociationDto> getAllWordAssociations(String relation) {
        if(relation.isEmpty()){
            return WordAssociationMapper.toDtoList(wordAssociationRepository.findAll());
        }
        return WordAssociationMapper.toDtoList(wordAssociationRepository.findAllWordAssociationsByRelation(
                Relation.toRelation(relation)));
    }

}
