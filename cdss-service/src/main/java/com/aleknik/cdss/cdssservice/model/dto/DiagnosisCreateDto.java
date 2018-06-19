package com.aleknik.cdss.cdssservice.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DiagnosisCreateDto {

    private long diseaseId;

    @NotNull
    @NotEmpty
    private List<Long> medicineIds;

    public DiagnosisCreateDto() {
    }

    public long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public List<Long> getMedicineIds() {
        return medicineIds;
    }

    public void setMedicineIds(List<Long> medicineIds) {
        this.medicineIds = medicineIds;
    }
}
