package com.epam.generator.model.jsresponse;

import java.util.List;

public class SolutionJsonResponse {
    private List<String> solutionEditions;
    private boolean validated;

    public List<String> getSolutionEditions() {
        return solutionEditions;
    }

    public void setSolutionEditions(List<String> solutionEditions) {
        this.solutionEditions = solutionEditions;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void addSolutionEdition(String solutionEdition) {
        this.solutionEditions.add(solutionEdition);
    }
}
