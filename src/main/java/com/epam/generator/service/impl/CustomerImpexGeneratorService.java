package com.epam.generator.service.impl;

import com.epam.generator.model.dto.CustomerDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.ImpexGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.epam.generator.utils.DataGeneratorConstants.CustomerConstants.CUSTOMER_COUNTRY;
import static com.epam.generator.utils.DataGeneratorConstants.CustomerConstants.CUSTOMER_KEY_POSTFIX;
import static com.epam.generator.utils.DataGeneratorConstants.CustomerConstants.CUSTOMER_STREET;
import static com.epam.generator.utils.DataGeneratorConstants.CustomerConstants.CUSTOMER_TOWN;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.CELLPHONE;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.COMMA;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.COUNTRY_ISO_DE;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.FAX;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.PHONE;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.POSTAL_CODE;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SAP_ADDRESS_USAGE_AG;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SAP_ADDRESS_USAGE_AP;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SAP_ADDRESS_USAGE_RC;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SAP_ADDRESS_USAGE_ZE;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SAP_ADDRESS_USAGE_ZK;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SAP_ADDRESS_USAGE_ZU;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SEMICOLON;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.STREETNAME;

@Component
public class CustomerImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/customers-impex.impex";

    @Autowired
    ResourceLoader resourceLoader;
    @Value("impex.labels.customer-org-header")
    private String customerOrgsHeader;
    @Value("${impex.labels.customer-orgs}")
    private String customerOrgsLabel;
    @Value("${impex.labels.customer-contact}")
    private String customerContactHeader;
    @Value("${impex.labels.customer-address}")
    private String customerAddressHeader;
    @Value("${impex.labels.customer-org-address-contact}")
    private String customerOrgContactAddressHeader;
    @Value("${impex.labels.customer-contact-address}")
    private String customerContactAddressHeader;
    @Value("${impex.labels.customer-partner-org-address}")
    private String partnerCustomerAddressHeader;


    @Override
    public void generate(final TestDataDTO testDataDTO) {

        LOG.info("----------CUSTOMER IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {

            generateCustomerOrg(testDataDTO, writer);
            generateContact(testDataDTO, writer);
            generateCustomerAddress(testDataDTO, writer);
            generateCustomerOrgContactAddress(testDataDTO, writer);
            generateCustomerContactAddress(testDataDTO, writer);
            generatePartnerOrgRelAddress(testDataDTO, writer);

        } catch (IOException e) {
            LOG.error("Shit happens", e.getCause());
        }

    }

    private void generateCustomerOrg(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write(customerOrgsHeader);
        writer.newLine();
        writer.write("# Customer Orgs");
        writer.newLine();
        writer.write(customerOrgsLabel);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            appendItem(writer, customerDTO.getUid());
            appendItem(writer, customerDTO.getName());
            appendItem(writer, testDataDTO.getPartnerDTO().getUid());
            appendItem(writer, customerDTO.getName());
            appendItem(writer, customerDTO.getName());

            appendItem(writer, "100");
            appendItem(writer, "35 million USD");
            appendItem(writer, "07");
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            appendItem(writer, customerDTO.getUid());
            appendDefault(writer);
            writer.newLine();
        }
    }

    private void generateContact(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Customer contacts");
        writer.newLine();
        writer.write(customerOrgsLabel);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            appendItem(writer, customerDTO.getCustomerContact().getUid());
            appendItem(writer, customerDTO.getUid());
            writer.append(appendGroupedItem(customerDTO.getCustomerContact().getGroups()));
            appendItem(writer, testDataDTO.getPartnerDTO().getUid());
            appendItem(writer, customerDTO.getCustomerContact().getCustomerId());
            appendItem(writer, "0001");
            appendItem(writer, customerDTO.getCustomerContact().getName());
            appendItem(writer, customerDTO.getCustomerContact().getEmail());
            appendItem(writer, "Business Manager");
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            writer.newLine();
        }
    }

    private void generateCustomerAddress(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Customer Org addresses");
        writer.newLine();
        writer.write(customerAddressHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            appendItem(writer, customerDTO.getUid());
            appendItem(writer, customerDTO.getUid());
            appendItem(writer, SAP_ADDRESS_USAGE_AG);
            appendItem(writer, customerDTO.getUid());
            appendItem(writer, STREETNAME);
            appendDefault(writer);
            appendItem(writer, POSTAL_CODE);
            appendItem(writer, "Berlin");
            appendItem(writer, COUNTRY_ISO_DE);
            appendItem(writer, FAX);
            appendItem(writer, PHONE);
            appendItem(writer, CELLPHONE);
            appendDefault(writer);
            writer.newLine();
        }
    }

    private void generateCustomerOrgContactAddress(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Customer Org contacts addresses");
        writer.newLine();
        writer.write(customerOrgContactAddressHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            createAddressForUsage(customerDTO, writer, SAP_ADDRESS_USAGE_ZE, "|666");
            createAddressForUsage(customerDTO, writer, SAP_ADDRESS_USAGE_ZU, "|777");
            createAddressForUsage(customerDTO, writer, SAP_ADDRESS_USAGE_RC, "|888");
            createAddressForUsage(customerDTO, writer, "", "|999");
        }

    }

    private void generateCustomerContactAddress(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Customer contact address");
        writer.newLine();
        writer.write(customerContactAddressHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            appendItem(writer, customerDTO.getCustomerContact().getUid());
            appendItem(writer, customerDTO.getCustomerContact().getUid() + "|" + CUSTOMER_KEY_POSTFIX);
            appendItem(writer, customerDTO.getUid());
            appendItem(writer, SAP_ADDRESS_USAGE_AP);
            appendItem(writer, CUSTOMER_STREET);
            appendItem(writer, "33");
            appendItem(writer, "1080");
            appendDefault(writer);
            appendDefault(writer);
            appendItem(writer, CUSTOMER_TOWN);
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            appendItem(writer, "000");
            appendDefault(writer);
            writer.newLine();
        }

    }

    private void generatePartnerOrgRelAddress(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Partner Org addresses (relationships to customer Orgs)");
        writer.newLine();
        writer.write(partnerCustomerAddressHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            appendItem(writer, testDataDTO.getPartnerDTO().getUid());
            appendItem(writer, customerDTO.getUid() + "|4001");
            appendItem(writer, customerDTO.getUid());
            appendItem(writer, SAP_ADDRESS_USAGE_ZK);
            appendItem(writer, CUSTOMER_STREET);
            appendItem(writer, "33");
            appendItem(writer, "1080");
            appendItem(writer, CUSTOMER_TOWN);
            appendItem(writer, CUSTOMER_COUNTRY);
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            appendDefault(writer);
            writer.newLine();
        }
    }

    private void createAddressForUsage(final CustomerDTO customerDTO, BufferedWriter writer, final String usage, final String idPostfix) throws IOException {
        appendItem(writer, customerDTO.getCustomerContact().getUid());
        appendItem(writer, customerDTO.getCustomerContact().getUid() + idPostfix);
        appendItem(writer, usage);
        appendItem(writer, customerDTO.getUid());
        appendItem(writer, STREETNAME);
        appendDefault(writer);
        appendItem(writer, POSTAL_CODE);
        appendItem(writer, "Berlin");
        appendItem(writer, COUNTRY_ISO_DE);
        appendItem(writer, FAX);
        appendItem(writer, PHONE);
        appendItem(writer, CELLPHONE);
        appendItem(writer, "000");
        writer.append(SEMICOLON);
        writer.newLine();
    }

    private void appendItem(BufferedWriter writer, final String item) throws IOException {
        writer.append(SEMICOLON);
        writer.append(item);
    }

    private void appendDefault(BufferedWriter writer) throws IOException {
        writer.append(SEMICOLON);
    }

    private String appendGroupedItem(final List<String> objects) {
        StringBuilder groupLine = new StringBuilder();

        groupLine.append(SEMICOLON);
        for (String object : objects) {
            groupLine.append(object);
            groupLine.append(COMMA);
        }
        groupLine.setLength(groupLine.length() - 1);
        return String.valueOf(groupLine);
    }
}
