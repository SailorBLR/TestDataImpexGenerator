package com.epam.generator.model.dto;

import java.util.ArrayList;
import java.util.List;

public class TestDataDTO {
    private String storyId;
    private int partnerNumber;
    private PartnerDTO partnerDTO;
    private int requiredCustomersQuantity;
    private int requiredManagersQuantity;
    private int requiredContactsQuantity;
    private int requiredAttachmentApproversQuantity;
    private List<CustomerDTO> customers;

    public TestDataDTO() {
        this.customers = new ArrayList<>();
    }

    public int getRequiredAttachmentApproversQuantity() {
        return requiredAttachmentApproversQuantity;
    }

    public void setRequiredAttachmentApproversQuantity(int requiredAttachmentApproversQuantity) {
        this.requiredAttachmentApproversQuantity = requiredAttachmentApproversQuantity;
    }

    public int getRequiredManagersQuantity() {
        return requiredManagersQuantity;
    }

    public void setRequiredManagersQuantity(int requiredManagersQuantity) {
        this.requiredManagersQuantity = requiredManagersQuantity;
    }

    public int getRequiredContactsQuantity() {
        return requiredContactsQuantity;
    }

    public void setRequiredContactsQuantity(int requiredContactsQuantity) {
        this.requiredContactsQuantity = requiredContactsQuantity;
    }

    public int getPartnerNumber() {
        return partnerNumber;
    }

    public void setPartnerNumber(int partnerNumber) {
        this.partnerNumber = partnerNumber;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public PartnerDTO getPartnerDTO() {
        return partnerDTO;
    }

    public void setPartnerDTO(PartnerDTO partnerDTO) {
        this.partnerDTO = partnerDTO;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    public void addCustomer(CustomerDTO customer) {
        this.customers.add(customer);
    }

    public int getRequiredCustomersQuantity() {
        return requiredCustomersQuantity;
    }

    public void setRequiredCustomersQuantity(int requiredCustomersQuantity) {
        this.requiredCustomersQuantity = requiredCustomersQuantity;
    }

    @Override public String toString() {
        return "TestDataDTO{" + "storyId='" + storyId + '\'' + ", partnerNumber=" + partnerNumber + ", partnerDTO="
                + partnerDTO + ", requiredCustomersQuantity=" + requiredCustomersQuantity + ", customers=" + customers
                + '}';
    }
}
