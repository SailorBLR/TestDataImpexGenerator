package com.epam.generator.model.jsresponse;


import com.epam.generator.model.dto.ContractDTO;

import java.util.ArrayList;
import java.util.List;

public class ContractJsonResponse {
    private List<ContractDTO> contractDTOList;
    private boolean validated;

    public ContractJsonResponse() {
        contractDTOList = new ArrayList<>();
    }

    public List<ContractDTO> getContractDTOList() {
        return contractDTOList;
    }

    public void setContractDTOList(List<ContractDTO> contractDTOList) {
        this.contractDTOList = contractDTOList;
    }

    public void addContractDTOs(List<ContractDTO> contractDTOList) {
        this.contractDTOList.addAll(contractDTOList);
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
