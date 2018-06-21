package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        final Patient user = findById(id);

        if (!user.getFirstName().equals(updatedPatient.getFirstName())) {
            user.setFirstName(updatedPatient.getFirstName());
        }
        if (!user.getLastName().equals(updatedPatient.getLastName())) {
            user.setLastName(updatedPatient.getLastName());
        }

        return patientRepository.save(user);
    }
}
