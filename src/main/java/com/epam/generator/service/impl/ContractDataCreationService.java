package com.epam.generator.service.impl;

import com.epam.generator.model.dto.ContractDTO;
import com.epam.generator.model.dto.CustomerDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.DataCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CUSTOMER_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.PROVIDER_CONTRACT_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.UNDERSCORE;

@Component
public class ContractDataCreationService implements DataCreationService {
    private static final Logger LOG = LoggerFactory.getLogger(ContractDataCreationService.class);

    @Override
    public void create(TestDataDTO testDataDTO) {
    }

    public void addContractToCustomer(final TestDataDTO testDataDTO, final ContractDTO contractDTO) {
        testDataDTO.getCustomers().stream()
                .filter(customer -> customer.getUid().equals(contractDTO.getCustomerUid()))
                .forEach(customer -> generateContractUidAndAdd(contractDTO, customer));
    }

    private void generateContractUidAndAdd(final ContractDTO contractDTO, final CustomerDTO customerDTO) {
        contractDTO.setUid(contractDTO.getCustomerUid()
                .replaceAll(CUSTOMER_PREFIX,PROVIDER_CONTRACT_PREFIX) + UNDERSCORE + (customerDTO.getContracts().size()+1));
        customerDTO.addContract(contractDTO);
        customerDTO.setPartnerContact(contractDTO.getPartnerContactUid());
    }
}
