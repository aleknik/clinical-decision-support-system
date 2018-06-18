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
        Set<Symptom> symptomsFirst = new HashSet<>();
        symptomsFirst.add(symptomService.findByName("Curenje iz nosa"));
        symptomsFirst.add(symptomService.findByName("Bol u grlu"));
        symptomsFirst.add(symptomService.findByName("Glavobolja"));
        symptomsFirst.add(symptomService.findByName("Kijanje"));
//        symptomsFirst.add(symptomService.findByName("Drhtavica"));
//        symptomsFirst.add(symptomService.findByName("Kašalj"));

        Set<Symptom> symptomsSecond = new HashSet<>();
        symptomsSecond.add(symptomService.findByName("6 meseci 10 slučajeva visok pritisak"));

        Set<Symptom> symptomsThird = new HashSet<>();
        symptomsThird.add(symptomService.findByName("Zamor"));
//        symptomsThird.add(symptomService.findByName("Nocturia"));
//        symptomsThird.add(symptomService.findByName("Otoci nogu i zglobova"));
//        symptomsThird.add(symptomService.findByName("Gušenje"));
//        symptomsThird.add(symptomService.findByName("Bol u grudima"));
//        symptomsThird.add(symptomService.findByName("Pacijent boluje od hipertenzije više od 6 meseci"));
        symptomsThird.add(symptomService.findByName("Pacijent boluje od dijabetesa"));

        symptomsFirst.addAll(symptomsSecond);
        symptomsFirst.addAll(symptomsThird);

        List<Disease> diseases = diagnosisReasonerService.diagnose(patient, symptomsFirst);

        return ResponseEntity.ok(diseases);
    }
}
