package com.epam.generator.service;

import com.epam.generator.model.dto.TestDataDTO;

/**
 * Interface provides data creation method
 */
public interface DataCreationService {

    /**
     * @param testDataDTO Updates DTO with required information
     */
    void create(TestDataDTO testDataDTO);
}
