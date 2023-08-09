package com.example.venly.repository.model;

public enum Relation {

    SYNONYM("synonim"), ANTONYM("antonym");

    private final String value;

    public String getValue() {
        return value;
    }

    private Relation(String value) {
        this.value = value;
    }

    public static Relation toRelation(String value) {
        for (Relation relation : values()) {
            if (relation.getValue().equals(value)) {
                return relation;
            }
        }
        throw new RuntimeException("Wrong value of Relation passed");
    }
}
