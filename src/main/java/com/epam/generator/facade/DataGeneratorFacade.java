package com.epam.generator.facade;

import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.impl.CustomerDataCreationService;
import com.epam.generator.service.impl.PartnerDataCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataGeneratorFacade {
    @Autowired
    PartnerDataCreationService partnerDataCreationService;
    @Autowired
    CustomerDataCreationService customerDataCreationService;


    public void generate (final TestDataDTO testDataDTO) {

        getPartnerDataCreationService().create(testDataDTO);
        getCustomerDataCreationService().create(testDataDTO);

    }

    public PartnerDataCreationService getPartnerDataCreationService() {
        return partnerDataCreationService;
    }

    public void setPartnerDataCreationService(PartnerDataCreationService partnerDataCreationService) {
        this.partnerDataCreationService = partnerDataCreationService;
    }

    public CustomerDataCreationService getCustomerDataCreationService() {
        return customerDataCreationService;
    }

    public void setCustomerDataCreationService(CustomerDataCreationService customerDataCreationService) {
        this.customerDataCreationService = customerDataCreationService;
    }

}
