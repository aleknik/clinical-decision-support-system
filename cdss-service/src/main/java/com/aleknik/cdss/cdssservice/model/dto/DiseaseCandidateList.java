package com.aleknik.cdss.cdssservice.model.dto;

import java.util.List;

public class DiseaseCandidateList {

    private List<DiseaseCandidate> diseaseCandidates;

    public List<DiseaseCandidate> getDiseaseCandidates() {
        return diseaseCandidates;
    }

    public void setDiseaseCandidates(List<DiseaseCandidate> diseaseCandidates) {
        this.diseaseCandidates = diseaseCandidates;
    }
}
