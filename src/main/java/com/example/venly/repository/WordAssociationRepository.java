package com.example.venly.repository;

import com.example.venly.repository.model.Relation;
import com.example.venly.repository.model.WordAssociation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordAssociationRepository extends JpaRepository<WordAssociation, Long> {

    @Query("SELECT wordAssociation from WordAssociation wordAssociation where wordAssociation.relation =?1")
    List<WordAssociation> findAllWordAssociationsByRelation(Relation relation);

    @Query("SELECT wordAssociation from WordAssociation wordAssociation where wordAssociation.firstWord =?1 AND wordAssociation.secondWord =?2")
    Optional<WordAssociation> findWordAssociationByFirstWordAndSecondWord(String firstWord, String secondWord);

}
