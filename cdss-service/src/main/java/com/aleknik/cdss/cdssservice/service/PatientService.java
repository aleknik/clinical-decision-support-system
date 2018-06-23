package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private final PatientRepository patientRepository;

    public Patient findById(long id) {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found."));
    }

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Patient updatedPatient, long id) {
        final Patient patient = findById(id);

        if (!patient.getFirstName().equals(updatedPatient.getFirstName())) {
            patient.setFirstName(updatedPatient.getFirstName());
        }
        if (!patient.getLastName().equals(updatedPatient.getLastName())) {
            patient.setLastName(updatedPatient.getLastName());
        }

        patient.setIngredientAllergies(updatedPatient.getIngredientAllergies());
        patient.setMedicineAllergies(updatedPatient.getMedicineAllergies());

        return patientRepository.save(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }
}
