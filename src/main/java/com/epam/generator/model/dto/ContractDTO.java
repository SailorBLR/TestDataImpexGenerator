package com.epam.generator.model.dto;

import java.time.LocalDate;

public class ContractDTO {
    private String uid;
    private String customerUid;
    private String solution;
    private String solutionEdition;
    private boolean manageable;
    private LocalDate startDate;
    private LocalDate endDate;
    private String orderType;
    private int orderEntries;
    private String partnerContactUid;
    private String contractType;
    private String predecessorId;

    public ContractDTO(){
        this.manageable = false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolutionEdition() {
        return solutionEdition;
    }

    public void setSolutionEdition(String solutionEdition) {
        this.solutionEdition = solutionEdition;
    }

    public boolean isManageable() {
        return manageable;
    }

    public void setManageable(boolean manageable) {
        this.manageable = manageable;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(int orderEntries) {
        this.orderEntries = orderEntries;
    }

    public String getPartnerContactUid() {
        return partnerContactUid;
    }

    public void setPartnerContactUid(String partnerContactUid) {
        this.partnerContactUid = partnerContactUid;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getPredecessorId() {
        return predecessorId;
    }

    public void setPredecessorId(String predecessorId) {
        this.predecessorId = predecessorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractDTO that = (ContractDTO) o;

        if (manageable != that.manageable) return false;
        if (orderEntries != that.orderEntries) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (customerUid != null ? !customerUid.equals(that.customerUid) : that.customerUid != null) return false;
        if (solution != null ? !solution.equals(that.solution) : that.solution != null) return false;
        if (solutionEdition != null ? !solutionEdition.equals(that.solutionEdition) : that.solutionEdition != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        return orderType != null ? orderType.equals(that.orderType) : that.orderType == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (customerUid != null ? customerUid.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        result = 31 * result + (solutionEdition != null ? solutionEdition.hashCode() : 0);
        result = 31 * result + (manageable ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + orderEntries;
        return result;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "uid='" + uid + '\'' +
                ", customerUid='" + customerUid + '\'' +
                ", solution='" + solution + '\'' +
                ", solutionEdition='" + solutionEdition + '\'' +
                ", manageable=" + manageable +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", orderType='" + orderType + '\'' +
                ", orderEntries=" + orderEntries +
                '}';
    }
}
