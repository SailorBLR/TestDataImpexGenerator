package com.epam.generator.facade;

import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.impl.ContractImpexGeneratorService;
import com.epam.generator.service.impl.ContractRelationsImpexGeneratorService;
import com.epam.generator.service.impl.CustomerImpexGeneratorService;
import com.epam.generator.service.impl.LicenseScopeImpexGeneratorService;
import com.epam.generator.service.impl.OrderImpexGeneratorService;
import com.epam.generator.service.impl.PartnerImpexGeneratorService;
import com.epam.generator.service.impl.TypesImpexGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Facade composes impex creation services
 */
@Component
public class ImpexGeneratorFacade {
    @Autowired
    private PartnerImpexGeneratorService partnerImpexGeneratorService;
    @Autowired
    private TypesImpexGeneratorService typesImpexGeneratorService;
    @Autowired
    private CustomerImpexGeneratorService customerImpexGeneratorService;
    @Autowired
    private ContractImpexGeneratorService contractImpexGeneratorService;
    @Autowired
    private OrderImpexGeneratorService orderImpexGeneratorService;
    @Autowired
    private ContractRelationsImpexGeneratorService contractRelationsImpexGeneratorService;
    @Autowired
    private LicenseScopeImpexGeneratorService licenseScopeImpexGeneratorService;


    public void generateImpexes(TestDataDTO testDataDTO) {
        getPartnerImpexGeneratorService().generate(testDataDTO);
        getTypesImpexGeneratorService().generate(testDataDTO);
        getCustomerImpexGeneratorService().generate(testDataDTO);
        getContractImpexGeneratorService().generate(testDataDTO);
        getOrderImpexGeneratorService().generate(testDataDTO);
        getContractRelationsImpexGeneratorService().generate(testDataDTO);
        getLicenseScopeImpexGeneratorService().generate(testDataDTO);
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

    public OrderImpexGeneratorService getOrderImpexGeneratorService() {
        return orderImpexGeneratorService;
    }

    public void setOrderImpexGeneratorService(OrderImpexGeneratorService orderImpexGeneratorService) {
        this.orderImpexGeneratorService = orderImpexGeneratorService;
    }

    public ContractRelationsImpexGeneratorService getContractRelationsImpexGeneratorService() {
        return contractRelationsImpexGeneratorService;
    }

    public void setContractRelationsImpexGeneratorService(
            ContractRelationsImpexGeneratorService contractRelationsImpexGeneratorService) {
        this.contractRelationsImpexGeneratorService = contractRelationsImpexGeneratorService;
    }

    public LicenseScopeImpexGeneratorService getLicenseScopeImpexGeneratorService() {
        return licenseScopeImpexGeneratorService;
    }

    public void setLicenseScopeImpexGeneratorService(
            LicenseScopeImpexGeneratorService licenseScopeImpexGeneratorService) {
        this.licenseScopeImpexGeneratorService = licenseScopeImpexGeneratorService;
    }
}
