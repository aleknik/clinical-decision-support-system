package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.model.Diagnosis;
import com.aleknik.cdss.cdssservice.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Diagnosis create(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }
}
