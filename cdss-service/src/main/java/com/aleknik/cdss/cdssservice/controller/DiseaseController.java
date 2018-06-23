package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.service.DiseaseService;
import com.aleknik.cdss.cdssservice.service.SymptomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DiseaseController {

    private final DiseaseService diseaseService;

    private final SymptomService symptomService;

    public DiseaseController(DiseaseService diseaseService, SymptomService symptomService) {
        this.diseaseService = diseaseService;
        this.symptomService = symptomService;
    }

    @GetMapping("/diseases")
    ResponseEntity findAll() {
        return ResponseEntity.ok(diseaseService.findAll());
    }

    @PostMapping("/diseases")
    ResponseEntity create(@RequestBody Disease disease) {

        Set<Symptom> specific = disease.getGeneralSymptoms().stream()
                .map(symptom -> symptomService.findById(symptom.getId())).collect(Collectors.toSet());

        Set<Symptom> general = disease.getGeneralSymptoms().stream()
                .map(symptom -> symptomService.findById(symptom.getId())).collect(Collectors.toSet());

        disease.setGeneralSymptoms(general);
        disease.setSpecificSymptoms(specific);

        disease = diseaseService.create(disease);

        return ResponseEntity.created(URI.create("")).body(disease);
    }
}
