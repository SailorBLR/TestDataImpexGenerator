package com.epam.generator.facade;

import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.impl.ContractImpexGeneratorService;
import com.epam.generator.service.impl.CustomerImpexGeneratorService;
import com.epam.generator.service.impl.PartnerImpexGeneratorService;
import com.epam.generator.service.impl.TypesImpexGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImpexGeneratorFacade {
    @Autowired
    PartnerImpexGeneratorService partnerImpexGeneratorService;
    @Autowired
    TypesImpexGeneratorService typesImpexGeneratorService;
    @Autowired
    CustomerImpexGeneratorService customerImpexGeneratorService;
    @Autowired
    ContractImpexGeneratorService contractImpexGeneratorService;


    public void generateImpexes(TestDataDTO testDataDTO) {
        getPartnerImpexGeneratorService().generate(testDataDTO);
        getTypesImpexGeneratorService().generate(testDataDTO);
        getCustomerImpexGeneratorService().generate(testDataDTO);
        getContractImpexGeneratorService().generate(testDataDTO);
    }

    public PartnerImpexGeneratorService getPartnerImpexGeneratorService() {
        return partnerImpexGeneratorService;
    }

    public void setPartnerImpexGeneratorService(PartnerImpexGeneratorService partnerImpexGeneratorService) {
        this.partnerImpexGeneratorService = partnerImpexGeneratorService;
    }

    public TypesImpexGeneratorService getTypesImpexGeneratorService() {
        return typesImpexGeneratorService;
    }

    public void setTypesImpexGeneratorService(TypesImpexGeneratorService typesImpexGeneratorService) {
        this.typesImpexGeneratorService = typesImpexGeneratorService;
    }

    public CustomerImpexGeneratorService getCustomerImpexGeneratorService() {
        return customerImpexGeneratorService;
    }

    public void setCustomerImpexGeneratorService(CustomerImpexGeneratorService customerImpexGeneratorService) {
        this.customerImpexGeneratorService = customerImpexGeneratorService;
    }

    public ContractImpexGeneratorService getContractImpexGeneratorService() {
        return contractImpexGeneratorService;
    }

    public void setContractImpexGeneratorService(ContractImpexGeneratorService contractImpexGeneratorService) {
        this.contractImpexGeneratorService = contractImpexGeneratorService;
    }
}
