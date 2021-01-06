package com.epam.generator.utils;

import com.epam.generator.model.entity.PartnerContact;

import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.*;

public class PartnerOrgGenUtil {

    private PartnerOrgGenUtil(){}

    public static String generateContactLine(final PartnerContact partnerContact, final String partnerUid) {

        StringBuilder contactLine = new StringBuilder();
        contactLine.append(SEMICOLON);
        contactLine.append(partnerContact.getUid()).append(SEMICOLON).append(partnerUid).append(SEMICOLON);
        for (String role : partnerContact.getGroups()) {
            contactLine.append(role).append(COMMA);
        }
        contactLine.setLength(contactLine.length() - 1);

        contactLine.append(SEMICOLON).append(TITLE).append(SEMICOLON).append(partnerContact.getName())
                .append(SEMICOLON).append(partnerContact.getEmail()).append(SEMICOLON).append(partnerContact.getCustomerId())
                .append(SEMICOLON).append(SEMICOLON).append(SEMICOLON).append(partnerUid);

        return String.valueOf(contactLine);
    }

    public static String generateOrgAddressLine(final String partnerUid, PartnerContact partnerContact) {
        StringBuilder addressLine = new StringBuilder();

        addressLine.append(SEMICOLON).append(partnerUid).append(SEMICOLON).append(partnerContact.getEmail()).append(SEMICOLON)
                .append(partnerContact.getCustomerId()).append(SEMICOLON).append(SAP_ADDRESS_USAGE_AG).append(SEMICOLON).append(STREETNAME)
                .append(SEMICOLON).append(POSTAL_CODE).append(SEMICOLON).append(TOWN).append(SEMICOLON).append(COUNTRY_ISO_DE).append(SEMICOLON)
                .append(FAX).append(SEMICOLON).append(PHONE).append(SEMICOLON).append(CELLPHONE).append(SEMICOLON).append(USAGE_COUNTER).append(SEMICOLON)
                .append(SEMICOLON);
        return String.valueOf(addressLine);
    }

    public static String generateOrgLine(final String partnerUid) {
        StringBuilder salesOrgLine = new StringBuilder();

        salesOrgLine.append(SEMICOLON).append(partnerUid).append(SALES_ORG_POSTFIX).append(SEMICOLON).append(SALES_ORG_NAME).append(partnerUid).append(COMMA)
                .append(CUSTOMERGROUP).append(COMMA).append(ODBUYGROUP).append(SEMICOLON).append(SALES_ORG_NAME).append(SEMICOLON).append(SALES_ORG_NAME)
                .append(SEMICOLON).append(SEMICOLON).append(SEMICOLON).append(SEMICOLON).append("true").append(SEMICOLON).append(COUNTRY_ISO_JP).append(SEMICOLON)
                .append("01").append(SEMICOLON).append("DAP").append(SEMICOLON).append("Tokyo").append(SEMICOLON).append("01").append(SEMICOLON).append("00");
        return String.valueOf(salesOrgLine);
    }

    public static String generateContactAddressLine(final PartnerContact partnerContact, final String partnerUid) {
        StringBuilder contactLine = new StringBuilder();

        contactLine.append(SEMICOLON).append(partnerContact.getUid()).append(SEMICOLON).append(partnerContact.getEmail()).append(SEMICOLON).append(partnerUid)
                .append(SEMICOLON).append(SAP_ADDRESS_USAGE_AP).append(SEMICOLON).append(STREETNAME).append(SEMICOLON).append(POSTAL_CODE).append(SEMICOLON)
                .append(TOWN).append(SEMICOLON).append(COUNTRY_ISO_DE).append(SEMICOLON).append(FAX).append(SEMICOLON).append(PHONE).append(SEMICOLON)
                .append(CELLPHONE).append(SEMICOLON).append(USAGE_COUNTER).append(SEMICOLON);

        return String.valueOf(contactLine);
    }

    public static String generateManagersRelationLine(final PartnerContact partnerContact, final String partnerUid) {
        StringBuilder relationLine = new StringBuilder();

        relationLine.append(SEMICOLON).append(partnerContact.getUid()).append(SEMICOLON).append(partnerUid).append(SEMICOLON).append(SOLUTION_TYPE_CLOUD_PART)
                .append(COMMA).append(SOLUTION_TYPE_CLOUD_SAP).append(COMMA).append(SOLUTION_TYPE_PREM).append(COMMA).append(SOLUTION_TYPE_BYD);

        return String.valueOf(relationLine);
    }

    public static String generateApproverRelationLine(final PartnerContact partnerContact, final String region, final String country) {
        StringBuilder approverLine = new StringBuilder();

        approverLine.append(SEMICOLON).append(country).append(SEMICOLON).append(region).append(SEMICOLON).append(partnerContact.getUid());

        return String.valueOf(approverLine);
    }

}
