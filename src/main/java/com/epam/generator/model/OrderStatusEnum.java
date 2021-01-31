package com.epam.generator.model;

/**
 * Possible order statuses enum
 */
public enum OrderStatusEnum {
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED"),
    PENDING_QUOTE("PENDING_QUOTE"),
    PENDING_APPROVAL("PENDING_APPROVAL");

    private String value;

    private OrderStatusEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
