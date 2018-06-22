package com.aleknik.cdss.cdssservice.model.dto;

import com.aleknik.cdss.cdssservice.model.Medicine;

import java.util.HashSet;
import java.util.Set;

public class Allergies {

    private Set<Medicine> medicines = new HashSet<>();

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
}
