package com.example.venly.service;

import static com.example.venly.service.dto.RelationStatus.YES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.venly.repository.WordAssociationRepository;
import com.example.venly.repository.model.Relation;
import com.example.venly.repository.model.WordAssociation;
import com.example.venly.service.dto.WordAssociationDto;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WordAssociationServiceTest {

    @InjectMocks
    private WordAssociationService wordAssociationService;

    @Mock
    private WordAssociationRepository wordAssociationRepository;

    @Test
    void shouldCreateWordAssociationWhenUnique() {
        //given
        WordAssociation wordAssociation = createWordAssociation();
        WordAssociationDto wordAssociationDto = new WordAssociationDto("test", "test2", Relation.toRelation("synonym"), YES);
        when(wordAssociationRepository.findWordAssociationByFirstWordAndSecondWord(wordAssociationDto.getFirstWord(), wordAssociationDto.getSecondWord())).thenReturn(
                Optional.empty());
        when(wordAssociationRepository.findWordAssociationByFirstWordAndSecondWord(wordAssociationDto.getSecondWord(), wordAssociationDto.getFirstWord())).thenReturn(
                Optional.empty());
        when(wordAssociationRepository.save(wordAssociation)).thenReturn(wordAssociation);
        //when
        WordAssociationDto response =  wordAssociationService.createWordAssociation(wordAssociationDto);

        //then
        verify(wordAssociationRepository, times(1)).save(any(WordAssociation.class));
        assertEquals(wordAssociationDto, response);

    }

    @Test
    void shouldNotCreateWordAssociationWhenCombinationExists() {
        //given
        WordAssociation wordAssociation = createWordAssociation();
        WordAssociationDto wordAssociationDto = new WordAssociationDto("test", "test2", Relation.toRelation("synonym"), YES);
        when(wordAssociationRepository.findWordAssociationByFirstWordAndSecondWord(wordAssociationDto.getFirstWord(), wordAssociationDto.getSecondWord())).thenReturn(
                Optional.of(wordAssociation));

        //when then
        assertThrows(RuntimeException.class, () ->wordAssociationService.createWordAssociation(wordAssociationDto));
        //then
        verify(wordAssociationRepository, times(0)).save(any(WordAssociation.class));

    }

    @Test
    void shouldNotCreateWordAssociationWhenReverseCombinationExists() {
        //given
        WordAssociation wordAssociation = createWordAssociation();
        WordAssociationDto wordAssociationDto = new WordAssociationDto("test", "test2", Relation.toRelation("synonym"), YES);
        when(wordAssociationRepository.findWordAssociationByFirstWordAndSecondWord(wordAssociationDto.getSecondWord(), wordAssociationDto.getFirstWord())).thenReturn(
                Optional.of(wordAssociation));

        //when then
        assertThrows(RuntimeException.class, () ->wordAssociationService.createWordAssociation(wordAssociationDto));
        //then
        verify(wordAssociationRepository, times(0)).save(any(WordAssociation.class));

    }

    @Test
    void shouldRetrieveAllWordAssociations() {
        //given
        WordAssociation wordAssociation = createWordAssociation();
        WordAssociationDto wordAssociationDto = new WordAssociationDto("test", "test2", Relation.toRelation("synonym"), YES);
        List<WordAssociationDto> wordAssociationDtos = List.of(wordAssociationDto);

        when(wordAssociationRepository.findAll()).thenReturn(List.of(wordAssociation));

        //when
        List<WordAssociationDto> response = wordAssociationService.getAllWordAssociations(null, false);
        //then
        assertEquals(wordAssociationDtos, response);

    }


    private WordAssociation createWordAssociation() {
        WordAssociation wordAssociation = new WordAssociation();
        wordAssociation.setFirstWord("test");
        wordAssociation.setSecondWord("test2");
        wordAssociation.setRelation(Relation.SYNONYM);
        return wordAssociation;
    }
}
