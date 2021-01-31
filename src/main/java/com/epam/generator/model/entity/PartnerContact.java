package com.epam.generator.model.entity;

import com.epam.generator.model.ContactTypeEnum;

import java.util.List;

public class PartnerContact {
    private String uid;
    private ContactTypeEnum contactTypeEnum;
    private String customerId;
    private String email;
    private List<String> groups;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ContactTypeEnum getContactTypeEnum() {
        return contactTypeEnum;
    }

    public void setContactTypeEnum(ContactTypeEnum contactTypeEnum) {
        this.contactTypeEnum = contactTypeEnum;
    }

    @Override public String toString() {
        return "PartnerContact{" + "uid='" + uid + '\'' + ", contactType=" + contactTypeEnum + '}';
    }
}
