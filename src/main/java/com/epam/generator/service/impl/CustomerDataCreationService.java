package com.epam.generator.service.impl;

import com.epam.generator.model.dto.CustomerDTO;
import com.epam.generator.model.entity.PartnerContact;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.DataCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.epam.generator.utils.DataGeneratorConstants.CustomerConstants.*;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.ID_SUFFIX;
import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.UNDERSCORE;

@Component
public class CustomerDataCreationService implements DataCreationService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerDataCreationService.class);

    @Override
    public void create(TestDataDTO testDataDTO) {
        LOG.info("CUSTOMERS DATA CREATION");
        for (int i = 1; i <= testDataDTO.getRequiredCustomersQuantity(); i++) {
            generateCustomer(testDataDTO, i);
        }

    }

    private void generateCustomer(final TestDataDTO testDataDTO, final int number) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUid(CUSTOMER_ORG_PREFIX + testDataDTO.getStoryId() + UNDERSCORE + testDataDTO.getPartnerNumber() + UNDERSCORE + number);
        customerDTO.setName(CUSTOMER_NAME_PREFIX + testDataDTO.getStoryId() + UNDERSCORE + testDataDTO.getPartnerNumber() + UNDERSCORE + number);
        customerDTO.setCustomerContact(generateContact(testDataDTO, number, customerDTO.getUid()));
        testDataDTO.addCustomer(customerDTO);
    }

    private PartnerContact generateContact(final TestDataDTO testDataDTO, final int number, final String customerId) {
        PartnerContact partnerContact = new PartnerContact();

        partnerContact.setUid(CUSTOMER_CONTACT_PREFIX + testDataDTO.getStoryId() + UNDERSCORE + testDataDTO.getPartnerNumber() + UNDERSCORE + number);
        partnerContact.setEmail(partnerContact.getUid());
        partnerContact.setGroups(Arrays.asList(customerId, B2BCUSTOMER_GROUP));
        partnerContact.setName(CUSTOMER_NAME_PREFIX + testDataDTO.getStoryId() + UNDERSCORE + testDataDTO.getPartnerNumber() + UNDERSCORE + number);
        partnerContact.setCustomerId(CUSTOMER_PREFIX + ID_SUFFIX + testDataDTO.getStoryId() + UNDERSCORE + testDataDTO.getPartnerNumber() + UNDERSCORE + number);

        return partnerContact;
    }
}
