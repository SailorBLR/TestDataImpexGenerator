package com.epam.generator.model;

/**
 * Possible solution editions enum
 */
public enum SolutionEditionEnum {
    CLOUD_SAP_START("$solEd_cloudSapStart"),
    CLOUD_SAP_PROF("$solEd_cloudSapProf"),
    CLOUD_PART_START("$solEd_cloudPartnerStart"),
    CLOUD_PART_PROF("$solEd_cloudPartnerProf"),
    PREM_START("$solEd_premStart"),
    PREM_PROF("$solEd_premProf"),
    BYD_CLASSIC("$solEd_bydcloudclassic"),
    BYD_SIMPL("$solEd_bydSimpl");

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
