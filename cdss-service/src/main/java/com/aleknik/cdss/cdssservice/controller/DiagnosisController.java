package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.service.DiagnosisReasonerService;
import com.aleknik.cdss.cdssservice.service.SymptomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class DiagnosisController {

    private final DiagnosisReasonerService diagnosisReasonerService;

    private final SymptomService symptomService;

    public DiagnosisController(DiagnosisReasonerService diagnosisReasonerService, SymptomService symptomService) {
        this.diagnosisReasonerService = diagnosisReasonerService;
        this.symptomService = symptomService;
    }

    @PostMapping("/{userId}/diagnose")
    public ResponseEntity diagnose() {
        Patient patient = new Patient();
        Set<Symptom> symptoms = new HashSet<>();
        symptoms.add(symptomService.findByName("Curenje iz nosa"));
        symptoms.add(symptomService.findByName("Bol u grlu"));
        symptoms.add(symptomService.findByName("Glavobolja"));
        symptoms.add(symptomService.findByName("Kijanje"));
        symptoms.add(symptomService.findByName("Drhtavica"));
        symptoms.add(symptomService.findByName("Kašalj"));
        List<Disease> diseases = diagnosisReasonerService.diagnose(patient, symptoms);

        return ResponseEntity.ok(diseases);
    }
}
