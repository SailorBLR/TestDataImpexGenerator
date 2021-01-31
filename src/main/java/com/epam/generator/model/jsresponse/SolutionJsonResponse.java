package com.epam.generator.model.jsresponse;

import com.epam.generator.model.SolutionEditionEnum;

import java.util.List;

/**
 * JSON response for adding Solution
 */
public class SolutionJsonResponse {
    private List<SolutionEditionEnum> solutionEditions;
    private boolean validated;

    public List<SolutionEditionEnum> getSolutionEditions() {
        return solutionEditions;
    }

    public void setSolutionEditions(List<SolutionEditionEnum> solutionEditions) {
        this.solutionEditions = solutionEditions;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void addSolutionEdition(SolutionEditionEnum solutionEdition) {
        this.solutionEditions.add(solutionEdition);
    }
}
