package com.aleknik.cdss.cdssservice.model.dto;

import com.aleknik.cdss.cdssservice.model.Disease;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisResultDto {

    private List<Disease> diseases = new ArrayList<>();

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
