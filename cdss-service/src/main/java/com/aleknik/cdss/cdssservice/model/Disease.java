package com.aleknik.cdss.cdssservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GroupEnum group;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "general_symptom",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName = "id"))
    private Set<Symptom> generalSymptoms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "specific_symptom",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName = "id"))
    private Set<Symptom> specificSymptoms;

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

    public Set<Symptom> getGeneralSymptoms() {
        return generalSymptoms;
    }

    public void setGeneralSymptoms(Set<Symptom> generalSymptoms) {
        this.generalSymptoms = generalSymptoms;
    }

    public Set<Symptom> getSpecificSymptoms() {
        return specificSymptoms;
    }

    public void setSpecificSymptoms(Set<Symptom> specificSymptoms) {
        this.specificSymptoms = specificSymptoms;
    }

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        return id == disease.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
