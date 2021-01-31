package com.epam.generator.model;

/**
 * Possible transaction types enum
 */
public enum TransactionTypeEnum {
    ZVA("ZVA"),
    ZVAL("ZVAL"),
    ZK04("ZK04"),
    ZK02("ZK02"),
    ZK09("ZK09");

    private String value;

    private TransactionTypeEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
