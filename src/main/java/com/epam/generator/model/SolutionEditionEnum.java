package com.epam.generator.model;

public enum SolutionEditionEnum {
    CLOUD_SAP_START("cloud_sap_start"),
    CLOUD_SAP_PROF("cloud_sap_prof"),
    CLOUD_PART_START("cloud_partner_start"),
    CLOUD_PART_PROF("cloud_partner_prof"),
    PREM_START("prem_start"),
    PREM_PROF("prem_prof"),
    BYD_CLASSIC("byd_cloud_classic"),
    BYD_SIMPL("byd_cloud_simplified");

    private String value;

    private SolutionEditionEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
