package com.example.venly.service;

import static com.example.venly.repository.model.Relation.toRelation;
import static com.example.venly.service.mapper.WordAssociationMapper.toDto;
import static com.example.venly.service.mapper.WordAssociationMapper.toDtoList;
import static com.example.venly.service.mapper.WordAssociationMapper.toModel;

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
        return toDto(wordAssociationRepository.save(toModel(wordAssociationDto)));
    }

    public List<WordAssociationDto> getAllWordAssociations(String relation) {
        if(relation.isEmpty()){
            return toDtoList(wordAssociationRepository.findAll());
        }
        return toDtoList(wordAssociationRepository.findAllWordAssociationsByRelation(
                toRelation(relation)));
    }

}
