package com.epam.generator.model;

public enum ContractTypeEnum {
    ZVA("ZVA"),
    ZVAL("ZVAL"),
    ZK02("ZK02");

    private String value;

    private ContractTypeEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
