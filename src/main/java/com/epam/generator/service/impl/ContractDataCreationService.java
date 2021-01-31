package com.epam.generator.service.impl;

import com.epam.generator.model.dto.ContractDTO;
import com.epam.generator.model.dto.CustomerDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.DataCreationService;
import com.epam.generator.utils.CustomerOrgGenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CUSTOMER_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.PROVIDER_CONTRACT_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.TEST_ORDER_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.UNDERSCORE;

@Component
public class ContractDataCreationService implements DataCreationService {
    private static final Logger LOG = LoggerFactory.getLogger(ContractDataCreationService.class);

    @Override
    public void create(TestDataDTO testDataDTO) {
    }

    public void addContractToCustomer(final TestDataDTO testDataDTO, final ContractDTO contractDTO) {
        testDataDTO.getCustomers().stream().filter(customer -> customer.getUid().equals(contractDTO.getCustomerUid()))
                .forEach(customer -> generateContractUidAndAdd(contractDTO, customer));
    }

    public void addOrderToContract(final TestDataDTO testDataDTO, final ContractDTO orderDTO) {
        if (!orderDTO.getPredecessorId().startsWith(TEST_ORDER_PREFIX)) {
            testDataDTO.getCustomers().forEach(customer -> customer.getContracts().stream()
                    .filter(contract -> contract.getUid().equals(orderDTO.getPredecessorId()))
                    .forEach(contract -> generateOrderUid(orderDTO, contract)));
        } else {
            Optional<ContractDTO> contractDTO = testDataDTO.getCustomers().stream()
                    .flatMap(customer -> customer.getContracts().stream()
                            .filter(contract -> contractContainsOrder(contract, orderDTO.getPredecessorId())))
                            .findFirst();
            contractDTO.ifPresent(dto -> generateOrderUid(orderDTO, dto));
        }
    }

    private void generateContractUidAndAdd(final ContractDTO contractDTO, final CustomerDTO customerDTO) {
        contractDTO
                .setUid(contractDTO.getCustomerUid().replaceAll(CUSTOMER_PREFIX, PROVIDER_CONTRACT_PREFIX) + UNDERSCORE
                        + (customerDTO.getContracts().size() + 1));
        contractDTO.setPredecessorId(CustomerOrgGenUtil.getGeneralContractUid(customerDTO));
        customerDTO.addContract(contractDTO);
        customerDTO.setPartnerContact(contractDTO.getPartnerContactUid());
    }

    private void generateOrderUid(final ContractDTO orderDTO, final ContractDTO contractDTO) {
        orderDTO.setUid(contractDTO.getUid().replaceAll(PROVIDER_CONTRACT_PREFIX, TEST_ORDER_PREFIX) + UNDERSCORE + (
                contractDTO.getOrders().size() + 1));
        orderDTO.setPartnerContactUid(contractDTO.getPartnerContactUid());
        orderDTO.setCustomerUid(contractDTO.getCustomerUid());
        contractDTO.addOrder(orderDTO);
        if(!orderDTO.getPredecessorId().equals(contractDTO.getUid())) {
            contractDTO.getOrders().stream()
                    .filter(order -> order.getUid().equals(orderDTO.getPredecessorId()))
                    .forEach(order -> order.addOrder(orderDTO));
        }
    }

    private boolean contractContainsOrder(final ContractDTO contractDTO, final String predecessorId) {
        List<ContractDTO> orders = contractDTO.getOrders().stream()
                .filter(order -> order.getUid().equals(predecessorId)).collect(Collectors.toList());
        return !orders.isEmpty();
    }
}
