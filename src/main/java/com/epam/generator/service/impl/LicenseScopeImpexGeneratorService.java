package com.epam.generator.service.impl;

import com.epam.generator.model.dto.ContractDTO;
import com.epam.generator.model.dto.CustomerDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.ImpexGenerator;
import com.epam.generator.utils.CustomerOrgGenUtil;
import com.epam.generator.utils.OrderEntriesGenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.BACKEND_CONTRACT_ID_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.BACKEND_ORDER_ID_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CATALOG_VARIABLE;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.TEST_ORDER_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SEMICOLON;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component public class LicenseScopeImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseScopeImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/license-scopes-impex.impex";

    @Autowired ResourceLoader resourceLoader;
    @Value("${impex.labels.license-scopes}") private String licenseScopeHeader;
    @Value("${impex.labels.license-items}") private String licenseItemsHeader;
    @Value("${impex.labels.license-orders}") private String licenseOrderHeader;
    @Value("${impex.labels.license-contract-relation}") private String licenseContractRelationHeader;

    @Override public void generate(TestDataDTO testDataDTO) {
        LOG.info("----------LICENSE SCOPE RELATIONS IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {
            writer.write("$lang = en");
            writer.newLine();
            for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
                if (!isEmpty(customerDTO.getContracts())) {
                    for (ContractDTO contractDTO : customerDTO.getContracts()) {
                        if (contractDTO.isManageable()) {
                            generateLicenseScopes(contractDTO, writer);
                            generateLicenseItems(contractDTO, writer);
                            generateLicenseOrders(contractDTO, writer);
                            generateLicenseContractRelations(contractDTO, writer);
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IT happens", e.getCause());
        }
    }

    private void generateLicenseScopes(final ContractDTO contractDTO, BufferedWriter writer) throws IOException {
        writer.write("# License Scopes");
        writer.newLine();
        writer.write(licenseScopeHeader);
        writer.newLine();
        appendItem(writer,
                BACKEND_CONTRACT_ID_PREFIX.concat(CustomerOrgGenUtil.getPartnerContractNumeric(contractDTO.getUid())));
        appendItem(writer, "false");
        writer.newLine();
    }

    private void generateLicenseItems(final ContractDTO contractDTO, BufferedWriter writer) throws IOException {
        writer.write("# License Items");
        writer.newLine();
        writer.write(licenseItemsHeader);
        writer.newLine();
        for (ContractDTO orderDTO : contractDTO.getOrders()) {
            for (int i = 0; i < orderDTO.getOrderEntries(); i++) {
                appendItem(writer, BACKEND_CONTRACT_ID_PREFIX
                        .concat(CustomerOrgGenUtil.getPartnerContractNumeric(contractDTO.getUid())));
                appendItem(writer, OrderEntriesGenUtil.getProductByNumberAndSolution(contractDTO.getSolution(), i).replace(CATALOG_VARIABLE, ""));
                appendItem(writer, String.valueOf(ThreadLocalRandom.current().nextInt(1, 9 + 1)));
                appendItem(writer, "902");
                appendItem(writer, BACKEND_CONTRACT_ID_PREFIX
                        .concat(CustomerOrgGenUtil.getPartnerContractNumeric(contractDTO.getUid())));
                writer.newLine();
            }
        }
    }

    private void generateLicenseOrders(final ContractDTO contractDTO, BufferedWriter writer) throws IOException {
        writer.write("# License Orders");
        writer.newLine();
        writer.write(licenseOrderHeader);
        writer.newLine();
        for (ContractDTO orderDTO : contractDTO.getOrders()) {
            appendItem(writer, orderDTO.getUid().replaceAll(TEST_ORDER_PREFIX, BACKEND_ORDER_ID_PREFIX));
            appendItem(writer, BACKEND_CONTRACT_ID_PREFIX
                    .concat(CustomerOrgGenUtil.getPartnerContractNumeric(contractDTO.getUid())));
            appendItem(writer, contractDTO.getContractType());
            appendItem(writer, BACKEND_CONTRACT_ID_PREFIX
                    .concat(CustomerOrgGenUtil.getPartnerContractNumeric(contractDTO.getUid())));
            writer.newLine();
        }

    }

    private void generateLicenseContractRelations(final ContractDTO contractDTO, BufferedWriter writer)
            throws IOException {
        writer.write("# Contract with License scope relations");
        writer.newLine();
        writer.write(licenseContractRelationHeader);
        writer.newLine();
        appendItem(writer, contractDTO.getUid());
        appendItem(writer,
                BACKEND_CONTRACT_ID_PREFIX.concat(CustomerOrgGenUtil.getPartnerContractNumeric(contractDTO.getUid())));
    }

    private void appendItem(BufferedWriter writer, final String item) throws IOException {
        writer.append(SEMICOLON);
        writer.append(item);
    }

}
