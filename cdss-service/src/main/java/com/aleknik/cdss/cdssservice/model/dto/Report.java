package com.aleknik.cdss.cdssservice.model.dto;

import com.aleknik.cdss.cdssservice.model.Patient;

import java.util.HashSet;
import java.util.Set;

public class Report {

    private Set<Patient> chronicPatients = new HashSet<>();

    private Set<Patient> addictPatients = new HashSet<>();

    private Set<Patient> weakImmuneSystemPatients = new HashSet<>();

    public Set<Patient> getChronicPatients() {
        return chronicPatients;
    }

    public void setChronicPatients(Set<Patient> chronicPatients) {
        this.chronicPatients = chronicPatients;
    }

    public Set<Patient> getAddictPatients() {
        return addictPatients;
    }

    public void setAddictPatients(Set<Patient> addictPatients) {
        this.addictPatients = addictPatients;
    }

    public Set<Patient> getWeakImmuneSystemPatients() {
        return weakImmuneSystemPatients;
    }

    public void setWeakImmuneSystemPatients(Set<Patient> weakImmuneSystemPatients) {
        this.weakImmuneSystemPatients = weakImmuneSystemPatients;
    }
}
