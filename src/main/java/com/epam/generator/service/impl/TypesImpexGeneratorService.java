package com.epam.generator.service.impl;

import com.epam.generator.model.dto.TestDataDTO;
import com.epam.generator.service.ImpexGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.epam.generator.utils.DataGeneratorConstants.PartnerConstants.*;

@Component
public class TypesImpexGeneratorService implements ImpexGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(TypesImpexGeneratorService.class);
    private static final String OUTPUT_PATH = "src/main/resources/partner-types-impex.impex";

    @Autowired
    ResourceLoader resourceLoader;
    @Value("${impex.labels.partner-types}")
    private String partnerTypesHeader;

    @Override
    public void generate(TestDataDTO testDataDTO) {
        LOG.info("----------PARTNER TYPES IMPEX GENERATION----------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH, false))) {

            writer.write("# Partner Types");
            writer.newLine();
            writer.write(partnerTypesHeader);
            writer.newLine();
            writer.write(lineForCategories("VAR", "", "GOPE", testDataDTO.getPartnerDTO().getUid()));
            writer.newLine();
            writer.write(lineForCategories("VAR", "B1PA", "", testDataDTO.getPartnerDTO().getUid()));
            writer.newLine();
            writer.write(lineForCategories("RUN", "", "GOPE", testDataDTO.getPartnerDTO().getUid()));
            writer.newLine();
            writer.write(lineForCategories("VAR", "CB1S", "", testDataDTO.getPartnerDTO().getUid()));
            writer.newLine();
            writer.write(lineForCategories("RUN", "CB1R", "", testDataDTO.getPartnerDTO().getUid()));

        } catch (IOException e) {
            LOG.error("Shit happens", e.getCause());
        }
    }

    private String lineForCategories(final String mainCategory, final String subCategory, final String program, final String partnerUid) {
        StringBuilder approverLine = new StringBuilder();

        approverLine.append(SEMICOLON).append(mainCategory).append(SEMICOLON).append(subCategory).append(SEMICOLON).append("P").append(SEMICOLON)
                .append(program).append(SEMICOLON).append("26.04.2010 00:00:00").append(SEMICOLON).append("26.04.2050 00:00:00").append(SEMICOLON)
                .append(partnerUid).append(SEMICOLON);

        return String.valueOf(approverLine);
    }
}
