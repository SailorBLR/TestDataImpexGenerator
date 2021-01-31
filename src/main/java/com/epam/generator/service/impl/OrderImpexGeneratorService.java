package com.epam.generator.service.impl;

import com.epam.generator.model.OrderTypeEnum;
import com.epam.generator.model.SolutionEditionEnum;
import com.epam.generator.model.SolutionEnum;
import com.epam.generator.model.dto.ContractDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.ImpexGenerator;
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

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.BACKEND_ORDER_ID_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.BASE_STORE;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CURRENCY_EUR;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.FORMATTER;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.TEST_ORDER_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.SEMICOLON;

@Component
public class OrderImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(OrderImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/orders-impex.impex";

    @Autowired ResourceLoader resourceLoader;
    @Value("${impex.labels.order-header}") private String orderHeader;
    @Value("${impex.labels.order}") private String orderLabel;
    @Value("${impex.labels.order-entry}") private String orderEntryHeader;

    @Override public void generate(TestDataDTO testDataDTO) {
        LOG.info("----------ORDERS IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {

            generateOrders(testDataDTO, writer);
            generateOrderEntries(testDataDTO, writer);

        } catch (IOException e) {
            LOG.error("Shit happens", e.getCause());
        }
    }

    private void generateOrders(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write(orderHeader);
        writer.newLine();
        writer.write("# Orders");
        writer.newLine();
        writer.write(orderLabel);
        writer.newLine();

        testDataDTO.getCustomers()
                .forEach(customer -> customer.getContracts().forEach(contract -> contract.getOrders().forEach(order -> {
                    try {
                        generateSingleOrder(testDataDTO, contract, order, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })));
    }

    private void generateOrderEntries(final TestDataDTO testDataDTO, BufferedWriter writer) throws IOException {
        writer.write("# Order Entries");
        writer.newLine();
        writer.write(orderEntryHeader);
        writer.newLine();

        testDataDTO.getCustomers()
                .forEach(customer -> customer.getContracts().forEach(contract -> contract.getOrders().forEach(order -> {
                    try {
                        generateEntriesForSingleOrder(contract, order, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })));
    }

    private void generateSingleOrder(final TestDataDTO testDataDTO, final ContractDTO contract, final ContractDTO order,
            BufferedWriter writer) throws IOException {
        appendItem(writer, order.getUid());
        appendItem(writer, contract.getPartnerContactUid());
        appendItem(writer, testDataDTO.getPartnerDTO().getUid());
        appendItem(writer, contract.getStartDate().format(FORMATTER));
        appendItem(writer, contract.getStartDate().format(FORMATTER));
        appendItem(writer, contract.getEndDate().format(FORMATTER));
        appendItem(writer, CURRENCY_EUR);
        appendItem(writer, String.valueOf(order.getEntriesPrice() * order.getOrderEntries()));
        appendItem(writer, order.getOrderStatus());
        appendItem(writer, BASE_STORE);
        appendItem(writer, "true");
        appendItem(writer, contract.getCustomerUid());
        appendItem(writer, order.getUid().replaceAll(TEST_ORDER_PREFIX, BACKEND_ORDER_ID_PREFIX));
        appendItem(writer, "CRM_PLANT");
        appendItem(writer, order.getOrderType());
        appendItem(writer, SolutionEditionEnum.valueOf(contract.getSolutionEdition()).getValue());
        appendItem(writer, SolutionEnum.valueOf(contract.getSolution()).getValue());
        writer.newLine();

    }

    private void generateEntriesForSingleOrder(final ContractDTO contract, final ContractDTO order,
            BufferedWriter writer) throws IOException {
        for (int i = 0; i < order.getOrderEntries(); i++) {
            appendItem(writer, order.getUid());
            appendItem(writer, OrderEntriesGenUtil.getProductByNumberAndSolution(contract.getSolution(), i));
            if (order.getOrderType().equals(OrderTypeEnum.REDUCTION.getValue()) || order.getOrderType()
                    .equals(OrderTypeEnum.DOWNGRADE.getValue())) {
                appendItem(writer, "-" + ThreadLocalRandom.current().nextInt(1, 9 + 1));
            } else {
                appendItem(writer, String.valueOf(ThreadLocalRandom.current().nextInt(1, 9 + 1)));
            }
            appendItem(writer, "902");
            appendItem(writer, String.valueOf(i + 1));
            appendDefault(writer);
            writer.newLine();
        }

    }

    private void appendItem(BufferedWriter writer, final String item) throws IOException {
        writer.append(SEMICOLON);
        writer.append(item);
    }

    private void appendDefault(BufferedWriter writer) throws IOException {
        writer.append(SEMICOLON);
    }
}
