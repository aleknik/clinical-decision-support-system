package com.aleknik.cdss.cdssservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @ManyToOne
    private Patient patient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "diagnosis_disease",
            joinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"))
    private Set<Disease> diseases;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "diagnosis_medicine",
            joinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> medicines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
}
