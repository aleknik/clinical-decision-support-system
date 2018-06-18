package com.aleknik.cdss.cdssservice.model.dto;

import com.aleknik.cdss.cdssservice.model.Symptom;

import java.util.Set;

public class SymptomListDto {

    private Set<Symptom> symptoms;

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public boolean contains(String name) {
        return symptoms.stream().anyMatch(symptom -> symptom.getName().equals(name));
    }
}
