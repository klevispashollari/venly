package com.example.venly.api;

import com.example.venly.service.WordAssociationService;
import com.example.venly.service.dto.WordAssociationDto;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/word-associations")
public class WordAssociationApi {

    private WordAssociationService wordAssociationService;

    public WordAssociationApi(WordAssociationService wordAssociationService) {
        this.wordAssociationService = wordAssociationService;
    }

    @PostMapping
    public WordAssociationDto createWordAssociation(@RequestBody WordAssociationDto request) {
        return wordAssociationService.createWordAssociation(request);
    }

    @GetMapping
    public List<WordAssociationDto> getAllWordAssociations() {
        return wordAssociationService.getAllWordAssociations();
    }
}
