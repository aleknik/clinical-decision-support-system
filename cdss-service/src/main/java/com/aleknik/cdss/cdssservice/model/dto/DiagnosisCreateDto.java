package com.aleknik.cdss.cdssservice.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DiagnosisCreateDto {

    @NotNull
    @NotEmpty
    private List<Long> diseaseIds;

    @NotNull
    @NotEmpty
    private List<Long> medicineIds;

    public DiagnosisCreateDto() {
    }

    public List<Long> getDiseaseIds() {
        return diseaseIds;
    }

    public void setDiseaseIds(List<Long> diseaseIds) {
        this.diseaseIds = diseaseIds;
    }

    public List<Long> getMedicineIds() {
        return medicineIds;
    }

    public void setMedicineIds(List<Long> medicineIds) {
        this.medicineIds = medicineIds;
    }
}
