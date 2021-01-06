package com.epam.generator.model;

public enum OrderTypeEnum {
    INITIAL("INITIAL"),
    REDUCTION("REDUCTION"),
    DOWNGRADE("DOWNGRADE"),
    ADDITION("ADDITION"),
    UPGRADE_ADDITION("UPGRADE_ADDITION"),
    UPGRADE("UPGRADE");

    private String value;

    private OrderTypeEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




}
