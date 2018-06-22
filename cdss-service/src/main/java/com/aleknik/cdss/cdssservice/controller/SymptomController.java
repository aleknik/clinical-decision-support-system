package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.model.SymptomType;
import com.aleknik.cdss.cdssservice.service.DiagnosisReasonerService;
import com.aleknik.cdss.cdssservice.service.DiseaseService;
import com.aleknik.cdss.cdssservice.service.SymptomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SymptomController {

    private final SymptomService symptomService;

    private final DiagnosisReasonerService diagnosisReasonerService;

    private final DiseaseService diseaseService;

    public SymptomController(SymptomService symptomService,
                             DiagnosisReasonerService diagnosisReasonerService,
                             DiseaseService diseaseService) {
        this.symptomService = symptomService;
        this.diagnosisReasonerService = diagnosisReasonerService;
        this.diseaseService = diseaseService;
    }

    @GetMapping("/symptoms")
    public ResponseEntity findAllSimple() {
        return ResponseEntity.ok(symptomService.findByType(SymptomType.SIMPLE));
    }

    @PostMapping("/symptoms/disease")
    ResponseEntity findSortedSymtoms(@RequestParam long diseaseId, @RequestBody List<Symptom> symptomList) {
        final Set<Symptom> symptoms = symptomList.stream()
                .map(symptom -> symptomService.findById(symptom.getId())).collect(Collectors.toSet());

        Disease disease = diseaseService.findById(diseaseId);

        return ResponseEntity.ok(diagnosisReasonerService.getSortedSymptoms(disease, symptoms));
    }
}
