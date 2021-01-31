package com.epam.generator.model.dto;

import com.epam.generator.model.entity.PartnerContact;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class CustomerDTO {
    private String uid;
    private String name;
    private PartnerContact customerContact;
    private String partnerContact;
    private List<ContractDTO> contracts;

    public CustomerDTO() {
        this.contracts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartnerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(PartnerContact customerContact) {
        this.customerContact = customerContact;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<ContractDTO> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDTO> contracts) {
        this.contracts = contracts;
    }

    public void addContract(ContractDTO contractDTO) {
        this.contracts.add(contractDTO);
    }

    public String getPartnerContact() {
        return partnerContact;
    }

    public void setPartnerContact(String partnerContact) {
        if (isNull(this.partnerContact)) {
            this.partnerContact = partnerContact;
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CustomerDTO that = (CustomerDTO) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (customerContact != null ? !customerContact.equals(that.customerContact) : that.customerContact != null)
            return false;
        return contracts != null ? contracts.equals(that.contracts) : that.contracts == null;
    }

    @Override public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (customerContact != null ? customerContact.hashCode() : 0);
        result = 31 * result + (contracts != null ? contracts.hashCode() : 0);
        return result;
    }
}
