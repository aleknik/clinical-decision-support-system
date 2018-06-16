package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.repository.SymptomRepository;
import org.springframework.stereotype.Service;

@Service
public class SymptomService {

    private final SymptomRepository symptomRepository;

    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public Symptom findById(long id) {
        return symptomRepository.findById(id).orElseThrow(() -> new NotFoundException("Symptom not found"));
    }

    public Symptom findByName(String name) {
        return symptomRepository.findByName(name).orElseThrow(() -> new NotFoundException("Symptom not found"));
    }
}
