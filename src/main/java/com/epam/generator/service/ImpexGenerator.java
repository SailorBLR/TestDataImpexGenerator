package com.epam.generator.service;

import com.epam.generator.model.dto.TestDataDTO;

/**
 * Interface provides behaviour for impex generation
 */
public interface ImpexGenerator {
    /**
     * @param testDataDTO Creates impex files from DTO
     */
    void generate(TestDataDTO testDataDTO);
}
