package com.epam.generator.service.impl;

import com.epam.generator.model.ContactTypeEnum;
import com.epam.generator.model.entity.PartnerContact;
import com.epam.generator.model.dto.PartnerDTO;
import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.DataCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.*;

@Component
public class PartnerDataCreationService implements DataCreationService {
    private static final Logger LOG = LoggerFactory.getLogger(PartnerDataCreationService.class);


    @Override
    public void create(TestDataDTO testDataDTO) {

        LOG.info("CREATING PARTNER DTO");
        testDataDTO.setPartnerDTO(generatePartner(testDataDTO.getStoryId(), testDataDTO.getPartnerNumber(), testDataDTO.getRequiredManagersQuantity(),
                testDataDTO.getRequiredContactsQuantity(), testDataDTO.getRequiredAttachmentApproversQuantity()));
    }

    private PartnerDTO generatePartner(final String storyId, final int partnerNumber, final int managersQuantity, final int contactsQuantity, final int approversQuantity) {

        String partnerUid = PARTNER_ORG_PREFIX + storyId + UNDERSCORE + partnerNumber;
        final PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setPartnerUsers(new ArrayList<>());
        partnerDTO.setUid(partnerUid);
        if (managersQuantity > 0) {
            partnerDTO.addPartnerUsers(generateContacts(managersQuantity, storyId, ContactTypeEnum.MANAGER, partnerNumber, partnerUid));
        }
        if (contactsQuantity > 0) {
            partnerDTO.addPartnerUsers(generateContacts(contactsQuantity, storyId, ContactTypeEnum.CONTACT, partnerNumber, partnerUid));
        }
        if (approversQuantity > 0) {
            partnerDTO.addPartnerUsers(generateContacts(approversQuantity, storyId, ContactTypeEnum.APPROVER, partnerNumber, partnerUid));
        }

        return partnerDTO;
    }

    private List<PartnerContact> generateContacts(final int quantity, final String storyId, final ContactTypeEnum contactType, final int partnerNumber, final String partnerUid) {
        List<PartnerContact> contacts = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            PartnerContact contact = new PartnerContact();
            switch (contactType) {
                case CONTACT:
                    contact.setUid(PARTNER_CONTACT_PREFIX + storyId + UNDERSCORE + partnerNumber + UNDERSCORE + i + EMAIL_POSTFIX);
                    contact.setGroups(Arrays.asList(partnerUid, CUSTOMERGROUP, ODBUYGROUP));
                    contact.setName(PARTNER_CONTACT);
                    break;
                case MANAGER:
                    contact.setUid(PARTNER_MANAGER_PREFIX + storyId + UNDERSCORE + partnerNumber + UNDERSCORE + i + EMAIL_POSTFIX);
                    contact.setGroups(Arrays.asList(partnerUid, CUSTOMERGROUP, MANAGERGROUP, ODBUYGROUP));
                    contact.setName(PARTNER_MANAGER);
                    break;
                case APPROVER:
                    contact.setUid(ATTACHMENT_APPROVER_PREFIX + storyId + UNDERSCORE + partnerNumber + UNDERSCORE + i + EMAIL_POSTFIX);
                    contact.setGroups(Arrays.asList(partnerUid, CUSTOMERGROUP, APPROVERGROUP, ODBUYGROUP));
                    contact.setName(ATTACHMENT_APPROVER);
                    break;
            }
            contact.setContactTypeEnum(contactType);
            contact.setCustomerId(PARTNER_PREFIX + ID_SUFFIX + storyId);
            contact.setEmail(contact.getUid());

            contacts.add(contact);
        }
        return contacts;
    }
}
