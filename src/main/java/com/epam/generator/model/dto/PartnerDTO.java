package com.epam.generator.model.dto;
//INSERT_UPDATE B2BUnit;uid[unique=true];Name;locName[lang=$lang];description;userTaxGroup(code);buyer[default =true];active[default =true];customerOrgs(uid);erpcustomerid


import com.epam.generator.model.entity.PartnerContact;

import java.util.List;

public class PartnerDTO {
    private String uid;
    private List<PartnerContact> partnerUsers;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<PartnerContact> getPartnerUsers() {
        return partnerUsers;
    }

    public void setPartnerUsers(List<PartnerContact> partnerUsers) {
        this.partnerUsers = partnerUsers;
    }

    public void addPartnerUsers(List<PartnerContact> partnerUsers) {
        this.partnerUsers.addAll(partnerUsers);
    }


    @Override
    public String toString() {
        return "PartnerDTO{" +
                "uid='" + uid + '\'' +
                ", partnerUsers=" + partnerUsers +
                '}';
    }
}
