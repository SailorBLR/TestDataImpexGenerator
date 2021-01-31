package com.epam.generator.service.impl;

import com.epam.generator.model.dto.ContractDTO;
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
import java.util.stream.Collectors;

import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.COMMA;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SEMICOLON;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component public class ContractRelationsImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ContractRelationsImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/contract-relations-impex.impex";

    @Autowired ResourceLoader resourceLoader;

    @Value("${impex.labels.order-relation-header}") private String orderRelationsHeader;
    @Value("${impex.labels.g-contract-relation-header}") private String gcRelationsHeader;
    @Value("${impex.labels.p-contract-relation-header}") private String pcRelationsHeader;

    @Override public void generate(TestDataDTO testDataDTO) {

        LOG.info("----------CONTRACT RELATIONS IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {
            writer.write("$lang = en");
            writer.newLine();
            generateOrdersRelations(testDataDTO, writer);
            generateProviderContractRelations(testDataDTO, writer);
            generateGeneralContractRelations(testDataDTO, writer);

        } catch (IOException e) {
            LOG.error("IT happens", e.getCause());
        }
    }

    private void generateOrdersRelations(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Orders relations");
        writer.newLine();
        writer.write(orderRelationsHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            if (!isEmpty(customerDTO.getContracts())) {
                for (ContractDTO contractDTO : customerDTO.getContracts()) {
                    generateOrdersForContracts(contractDTO, writer);
                }
            }
        }
    }

    private void generateOrdersForContracts(final ContractDTO contractDTO, BufferedWriter writer) throws IOException {
        for (ContractDTO orderDTO : contractDTO.getOrders()) {
            appendItem(writer, orderDTO.getUid());
            if (isEmpty(orderDTO.getOrders())) {
                appendDefault(writer);
            } else {
                writer.append(appendGroupedItem(
                        orderDTO.getOrders().stream().map(ContractDTO::getUid).collect(Collectors.toList())));
            }

            appendItem(writer, orderDTO.getPredecessorId());
            writer.newLine();
        }

    }

    private void generateProviderContractRelations(final TestDataDTO testDataDTO, BufferedWriter writer)
            throws IOException {
        writer.write("# Provider Contracts relations");
        writer.newLine();
        writer.write(pcRelationsHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            if (!isEmpty(customerDTO.getContracts())) {
                for (ContractDTO contractDTO : customerDTO.getContracts()) {
                    appendItem(writer, contractDTO.getUid());
                    if (isEmpty(contractDTO.getOrders())) {
                        appendDefault(writer);
                    } else {
                        writer.append(appendGroupedItem(contractDTO.getOrders().stream().map(ContractDTO::getUid)
                                .collect(Collectors.toList())));
                    }
                    appendItem(writer, contractDTO.getPredecessorId());
                    if (!isEmpty(contractDTO.getOrders())) {
                        appendItem(writer, contractDTO.getOrders().get(0).getUid());
                    }
                    writer.newLine();
                }
            }
        }
    }

    private void generateGeneralContractRelations(final TestDataDTO testDataDTO, BufferedWriter writer)
            throws IOException {
        writer.write("# General Contracts relations");
        writer.newLine();
        writer.write(gcRelationsHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            if (!isEmpty(customerDTO.getContracts())) {
                for (ContractDTO contractDTO : customerDTO.getContracts()) {
                    appendItem(writer, contractDTO.getPredecessorId());
                    appendItem(writer, contractDTO.getUid());
                    writer.newLine();
                }
            }
        }
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
