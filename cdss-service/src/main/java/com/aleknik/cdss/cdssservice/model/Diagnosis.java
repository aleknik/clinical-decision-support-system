package com.aleknik.cdss.cdssservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Role(Role.Type.EVENT)
@Timestamp("date")
@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("diagnoses")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    private Disease disease;

    @ManyToOne(fetch = FetchType.EAGER)
    private User doctor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "diagnosis_medicine",
            joinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> medicines = new HashSet<>();

    public boolean hasMedicineType(MedicineType medicineType) {
        return medicines.stream().anyMatch(medicine -> medicine.getMedicineType() == medicineType);
    }

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

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
}
