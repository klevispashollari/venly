package com.example.venly.repository.model;

import static com.example.venly.utils.Constants.WRONG_RELATION_VALUE_ERROR_MESSAGE;

public enum Relation {

    SYNONYM("synonym"), ANTONYM("antonym");

    private final String value;

    public String getValue() {
        return value;
    }

    Relation(String value) {
        this.value = value;
    }

    public static Relation toRelation(String value) {
        for (Relation relation : values()) {
            if (relation.getValue().equals(value)) {
                return relation;
            }
        }
        throw new RuntimeException(WRONG_RELATION_VALUE_ERROR_MESSAGE);
    }
}
