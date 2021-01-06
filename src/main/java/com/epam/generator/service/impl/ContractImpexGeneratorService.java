package com.epam.generator.service.impl;

import com.epam.generator.model.SolutionEditionEnum;
import com.epam.generator.model.SolutionEnum;
import com.epam.generator.model.dto.ContractDTO;
import com.epam.generator.model.dto.CustomerDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.ImpexGenerator;
import com.epam.generator.utils.CustomerOrgGenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.BACKEND_CONTRACT_ID_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CURRENCY_EUR;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.DATE_FORMAT;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.GENERAL_CONTRACT_DESCRIPTION;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.PROVIDER_CONTRACT_DESCRIPTION;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SEMICOLON;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class ContractImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ContractImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/contracts-impex.impex";

    @Autowired
    ResourceLoader resourceLoader;
    @Value("${impex.labels.provider-contract}")
    private String providerContractHeader;
    @Value("${impex.labels.general-contract}")
    private String generalContractHeader;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);


    @Override
    public void generate(TestDataDTO testDataDTO) {
        LOG.info("----------CONTRACTS IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {

            generateProviderContracts(testDataDTO, writer);
            generateGeneralContract(testDataDTO, writer);

        } catch (IOException e) {
            LOG.error("Shit happens", e.getCause());
        }
    }

    private void generateGeneralContract(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# General Contracts");
        writer.newLine();
        writer.write(generalContractHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            if (!isEmpty(customerDTO.getContracts())) {
                appendItem(writer, CustomerOrgGenUtil.getGeneralContractUid(customerDTO));
                appendItem(writer, customerDTO.getPartnerContact());
                appendItem(writer, LocalDateTime.now().format(FORMATTER));
                appendItem(writer, CURRENCY_EUR);
                appendItem(writer, testDataDTO.getPartnerDTO().getUid());
                appendItem(writer, customerDTO.getCustomerContact().getUid());
                appendItem(writer, customerDTO.getUid());
                appendItem(writer, GENERAL_CONTRACT_DESCRIPTION);
                writer.newLine();
            }
        }
    }

    private void generateProviderContracts(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Provider Contracts");
        writer.newLine();
        writer.write(providerContractHeader);
        writer.newLine();
        for (CustomerDTO customerDTO : testDataDTO.getCustomers()) {
            for (ContractDTO contract : customerDTO.getContracts()) {
                writer.newLine();
                appendItem(writer, contract.getUid());
                appendItem(writer, customerDTO.getPartnerContact());
                appendItem(writer, contract.getStartDate().format(FORMATTER));
                appendItem(writer, contract.getStartDate().format(FORMATTER));
                appendItem(writer, CURRENCY_EUR);
                appendItem(writer, contract.getEndDate().format(FORMATTER));
                appendItem(writer, SolutionEnum.valueOf(contract.getSolution()).getValue());
                appendItem(writer, contract.getSolutionEdition());
                appendItem(writer, PROVIDER_CONTRACT_DESCRIPTION);
                appendItem(writer, BACKEND_CONTRACT_ID_PREFIX.concat(
                        CustomerOrgGenUtil.getPartnerContractNumeric(contract.getUid())));
                appendItem(writer, contract.getContractType());

            }
        }
        writer.newLine();
    }

    private void appendItem(BufferedWriter writer, final String item) throws IOException {
        writer.append(SEMICOLON);
        writer.append(item);
    }

    private void appendDefault(BufferedWriter writer) throws IOException {
        writer.append(SEMICOLON);
    }
}
