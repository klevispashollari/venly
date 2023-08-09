package com.example.venly.service.dto;

import static com.example.venly.utils.Constants.WRONG_WORD_FORMAT_NAME;

import com.example.venly.repository.model.Relation;
import jakarta.validation.constraints.Pattern;

public class WordAssociationDto {

    @Pattern(regexp = "^[a-zA-Z]+$", message = WRONG_WORD_FORMAT_NAME)
    private String firstWord;
    @Pattern(regexp = "^[a-zA-Z]+$", message = WRONG_WORD_FORMAT_NAME)
    private String secondWord;
    private Relation relation;
    // couldn't think of a better name :(
    private RelationStatus relationStatus;

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public RelationStatus getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(RelationStatus relationStatus) {
        this.relationStatus = relationStatus;
    }

    public WordAssociationDto() {
        super();
    }

    public WordAssociationDto(String firstWord, String secondWord, Relation relation,
            RelationStatus relationStatus) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
        this.relation = relation;
        this.relationStatus = relationStatus;
    }
}
