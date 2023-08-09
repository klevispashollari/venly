package com.example.venly.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "word-association")
public class WordAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String firstWord;

    @Column
    private String secondWord;

    @Enumerated(EnumType.ORDINAL)
    private Relation relation;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WordAssociation that = (WordAssociation) o;

        if (getId() != that.getId()) {
            return false;
        }
        if (!getFirstWord().equals(that.getFirstWord())) {
            return false;
        }
        if (!getSecondWord().equals(that.getSecondWord())) {
            return false;
        }
        return getRelation() == that.getRelation();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getFirstWord().hashCode();
        result = 31 * result + getSecondWord().hashCode();
        result = 31 * result + getRelation().hashCode();
        return result;
    }
}
