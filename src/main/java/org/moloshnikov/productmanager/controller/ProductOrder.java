package org.moloshnikov.productmanager.controller;

public enum ProductOrder {
    ID("id"), // default
    PRICE("price"),
    NAME("name");

    private final String fieldName;

    ProductOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
