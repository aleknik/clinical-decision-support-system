package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Diagnosis;
import com.aleknik.cdss.cdssservice.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Diagnosis create(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    public Diagnosis findById(long id) {
        return diagnosisRepository.findById(id).orElseThrow(() -> new NotFoundException("Diagnosis not found"));
    }

    public List<Diagnosis> findByPatient(long patientId) {
        return diagnosisRepository.findByPatientId(patientId);
    }
}
