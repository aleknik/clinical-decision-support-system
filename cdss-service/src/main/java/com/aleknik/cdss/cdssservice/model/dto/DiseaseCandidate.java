package com.aleknik.cdss.cdssservice.model.dto;

import com.aleknik.cdss.cdssservice.model.Disease;

public class DiseaseCandidate {

    private Disease disease;

    private int generalSymptomCount;

    private int specificSymptomCount;

    public DiseaseCandidate(Disease disease, int generalSymptomCount, int specificSymptomCount) {
        this.disease = disease;
        this.generalSymptomCount = generalSymptomCount;
        this.specificSymptomCount = specificSymptomCount;
    }

    public int getSymptomCount() {
        return generalSymptomCount + specificSymptomCount;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getGeneralSymptomCount() {
        return generalSymptomCount;
    }

    public void setGeneralSymptomCount(int generalSymptomCount) {
        this.generalSymptomCount = generalSymptomCount;
    }

    public int getSpecificSymptomCount() {
        return specificSymptomCount;
    }

    public void setSpecificSymptomCount(int specificSymptomCount) {
        this.specificSymptomCount = specificSymptomCount;
    }
}
