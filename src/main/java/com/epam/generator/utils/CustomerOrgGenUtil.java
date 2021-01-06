package com.epam.generator.utils;

import com.epam.generator.model.dto.CustomerDTO;

import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.CUSTOMER_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.GENERAL_CONTRACT_PREFIX;
import static com.epam.generator.utils.DataGeneratorConstants.ContractConstants.PROVIDER_CONTRACT_PREFIX;

public class CustomerOrgGenUtil {
    public static String getGeneralContractUid(final CustomerDTO customerDTO) {
        return customerDTO.getUid()
                .replaceAll(CUSTOMER_PREFIX, GENERAL_CONTRACT_PREFIX);
    }

    public static String getPartnerContractNumeric(final String contractId) {
        return contractId.replaceAll(PROVIDER_CONTRACT_PREFIX, "");
    }
}
