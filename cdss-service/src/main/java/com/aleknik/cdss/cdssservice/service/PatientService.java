package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

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
}
