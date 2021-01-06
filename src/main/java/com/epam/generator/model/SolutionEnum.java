package com.epam.generator.model;

public enum SolutionEnum {

    PREM("prem"),
    CLOUD_SAP("cloud_sap"),
    CLOUD_PARTNER("cloud_part"),
    BYD_CLOUD("byd_cloud");

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
