package com.epam.generator.service.impl;

import com.epam.generator.model.ContactTypeEnum;
import com.epam.generator.model.entity.PartnerContact;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.ImpexGenerator;
import com.epam.generator.utils.PartnerOrgGenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.COUNTRY_ISO_DE;

@Component
public class PartnerImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(PartnerImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/partner-units-impex.impex";

    @Autowired
    ResourceLoader resourceLoader;
    @Value("${impex.labels.partner-org}")
    private String partnerOrgHeader;
    @Value("${impex.labels.partner-contacts}")
    private String partnerContactsHeader;
    @Value("${impex.labels.partner-org-address}")
    private String partnerOrgAddressHeader;
    @Value("${impex.labels.partner-org-unit}")
    private String partnerOrgUnitsHeader;
    @Value("${impex.labels.partner-contact-address}")
    private String partnerContactAddressHeader;
    @Value("${impex.labels.partner-manager-relations}")
    private String partnerManagerRelationsHeader;
    @Value("${impex.labels.approver-location-relations}")
    private String attachmentApproverRelationsHeader;
    @Value("${impex.util.semicolon}")
    private String semicolon;
    @Value("${impex.default.name}")
    private String name;
    @Value("${impex.default.locName}")
    private String locName;
    @Value("${impex.default.description}")
    private String description;


    @Override
    public void generate(TestDataDTO testDataDTO) {
        LOG.info("----------PARTNER IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {
            generatePartnerOrg(testDataDTO, writer);
            generatePartnerContacts(testDataDTO, writer);
            generatePartnerOrgAddress(testDataDTO, writer);
            generatePartnerOrgSalesUnit(testDataDTO, writer);
            generateContactAddresses(testDataDTO, writer);
            generateManagerRelation(testDataDTO, writer);
            generateApproversRegions(testDataDTO, writer);

        } catch (IOException e) {
            LOG.error("Shit happens", e.getCause());
        }

    }

    private void appendItem(BufferedWriter writer, final String item) throws IOException {
        writer.append(semicolon);
        writer.append(item);
    }

    private void appendDefault(BufferedWriter writer) throws IOException {
        writer.append(semicolon);
    }

    private void generatePartnerOrg(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Partner Orgs");
        writer.newLine();
        writer.write(partnerOrgHeader);
        writer.newLine();
        appendItem(writer, testDataDTO.getPartnerDTO().getUid());
        appendItem(writer, name);
        appendItem(writer, locName);
        appendItem(writer, description);
        appendDefault(writer);
        appendDefault(writer);
        appendDefault(writer);
        appendDefault(writer);
        appendItem(writer, testDataDTO.getPartnerDTO().getUid());
        writer.append(semicolon);
    }

    private void generatePartnerContacts(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.write("# Partner contacts");
        writer.newLine();
        writer.write(partnerContactsHeader);
        writer.newLine();
        for (PartnerContact partnerContact : testDataDTO.getPartnerDTO().getPartnerUsers()) {
            writer.write(PartnerOrgGenUtil.generateContactLine(partnerContact, testDataDTO.getPartnerDTO().getUid()));
            writer.newLine();
        }
    }

    private void generatePartnerOrgAddress(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.write("# Partner Org addresses");
        writer.newLine();
        writer.write(partnerOrgAddressHeader);
        writer.newLine();
        for (PartnerContact partnerContact : testDataDTO.getPartnerDTO().getPartnerUsers()) {
            if (partnerContact.getContactTypeEnum().equals(ContactTypeEnum.CONTACT)) {
                writer.write(PartnerOrgGenUtil.generateOrgAddressLine(testDataDTO.getPartnerDTO().getUid(), partnerContact));
                writer.newLine();
            }
        }
    }

    private void generatePartnerOrgSalesUnit(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.write("# Partner Orgs Sales units");
        writer.newLine();
        writer.write(partnerOrgUnitsHeader);
        writer.newLine();
        writer.write(PartnerOrgGenUtil.generateOrgLine(testDataDTO.getPartnerDTO().getUid()));
    }

    private void generateContactAddresses(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.write("# Partner Contact addresses");
        writer.newLine();
        writer.write(partnerContactAddressHeader);
        writer.newLine();
        for (PartnerContact partnerContact : testDataDTO.getPartnerDTO().getPartnerUsers()) {
            if (partnerContact.getContactTypeEnum().equals(ContactTypeEnum.CONTACT)) {
                writer.write(PartnerOrgGenUtil.generateContactAddressLine(partnerContact, testDataDTO.getPartnerDTO().getUid()));
                writer.newLine();
            }
        }
    }

    private void generateManagerRelation(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.write("# Partner Managers relations to units and solutions");
        writer.newLine();
        writer.write(partnerManagerRelationsHeader);
        writer.newLine();
        for (PartnerContact partnerContact : testDataDTO.getPartnerDTO().getPartnerUsers()) {
            if (partnerContact.getContactTypeEnum().equals(ContactTypeEnum.MANAGER)) {
                writer.write(PartnerOrgGenUtil.generateManagersRelationLine(partnerContact, testDataDTO.getPartnerDTO().getUid()));
                writer.newLine();
            }
        }
    }

    private void generateApproversRegions(TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.write("# Attachment Approvers location relations");
        writer.newLine();
        writer.write(attachmentApproverRelationsHeader);
        writer.newLine();
        for (PartnerContact partnerContact : testDataDTO.getPartnerDTO().getPartnerUsers()) {
            if (partnerContact.getContactTypeEnum().equals(ContactTypeEnum.APPROVER)) {
                writer.write(PartnerOrgGenUtil.generateApproverRelationLine(partnerContact, "MEE", COUNTRY_ISO_DE));
                writer.newLine();
                writer.write(PartnerOrgGenUtil.generateApproverRelationLine(partnerContact, "EMEA", "ES"));
                writer.newLine();
                writer.write(PartnerOrgGenUtil.generateApproverRelationLine(partnerContact, "APJ", "MY"));
                writer.newLine();

            }
        }
    }

}
