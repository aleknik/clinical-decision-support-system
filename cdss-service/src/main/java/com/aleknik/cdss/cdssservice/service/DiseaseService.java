package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public Disease findById(long id) {
        return diseaseRepository.findById(id).orElseThrow(() -> new NotFoundException("Disease not found."));
    }

    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }
}
