package org.moloshnikov.productmanager.controller.order;

public enum ArticleOrder {
    ID("id"),
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
