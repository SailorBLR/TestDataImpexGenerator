package com.epam.generator.model;

/**
 * Solution enum
 */
public enum SolutionEnum {

    PREM("$sol_prem"),
    CLOUD_SAP("$sol_cloudSap"),
    CLOUD_PARTNER("$sol_cloudPartner"),
    BYD_CLOUD("$solution_byd");

    private String value;

    private SolutionEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
