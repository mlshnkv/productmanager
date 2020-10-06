package org.moloshnikov.productmanager.controller;

public enum ArticleOrder {
    ID("id"), // default
    DATE("created"),
    NAME("name");

    private final String fieldName;

    ArticleOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
