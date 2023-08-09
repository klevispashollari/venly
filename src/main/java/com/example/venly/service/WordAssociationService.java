package com.example.venly.service;

import static com.example.venly.repository.model.Relation.toRelation;
import static com.example.venly.service.mapper.WordAssociationMapper.toDto;
import static com.example.venly.service.mapper.WordAssociationMapper.toDtoList;
import static com.example.venly.service.mapper.WordAssociationMapper.toModel;
import static com.example.venly.utils.Constants.RELATION_ALREADY_EXISTS_ERROR_MESSAGE;

import com.example.venly.repository.WordAssociationRepository;
import com.example.venly.repository.model.WordAssociation;
import com.example.venly.service.dto.WordAssociationDto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class WordAssociationService {

    private final WordAssociationRepository wordAssociationRepository;

    public WordAssociationService(
            WordAssociationRepository wordAssociationRepository) {
        this.wordAssociationRepository = wordAssociationRepository;
    }

    public WordAssociationDto createWordAssociation(WordAssociationDto wordAssociationDto) {
        Optional<WordAssociation> existingWordAssociation = wordAssociationRepository.findWordAssociationByFirstWordAndSecondWord(wordAssociationDto.getFirstWord(), wordAssociationDto.getSecondWord());
        if(existingWordAssociation.isPresent()) {
            throw new RuntimeException(RELATION_ALREADY_EXISTS_ERROR_MESSAGE);
        }
        //executed extra call to database for the sake of splitting the implementation of the tasks.
        //preferably we should do only 1 database call to do both checks at the same time.
        Optional<WordAssociation> existingReverseWordAssociation = wordAssociationRepository.findWordAssociationByFirstWordAndSecondWord(wordAssociationDto.getSecondWord(), wordAssociationDto.getFirstWord());
        if(existingReverseWordAssociation.isPresent()) {
            throw new RuntimeException(RELATION_ALREADY_EXISTS_ERROR_MESSAGE);
        }
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
