package com.aleknik.cdss.cdssservice.model;

import javax.persistence.*;

@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SymptomType symptomType;

    public Symptom() {
    }

    public Symptom(String name, SymptomType symptomType) {
        this.name = name;
        this.symptomType = symptomType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymptomType getSymptomType() {
        return symptomType;
    }

    public void setSymptomType(SymptomType symptomType) {
        this.symptomType = symptomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symptom symptom = (Symptom) o;

        return id == symptom.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
