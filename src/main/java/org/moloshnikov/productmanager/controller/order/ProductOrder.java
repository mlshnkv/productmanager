package org.moloshnikov.productmanager.controller.order;

public enum ProductOrder {
    ID("id"),
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