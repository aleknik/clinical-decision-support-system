package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.*;
import com.aleknik.cdss.cdssservice.model.dto.DiagnosisCreateDto;
import com.aleknik.cdss.cdssservice.model.dto.IdListDto;
import com.aleknik.cdss.cdssservice.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class DiagnosisController {

    private final DiagnosisReasonerService diagnosisReasonerService;

    private final MedicineService medicineService;

    private final DiseaseService diseaseService;

    private final SymptomService symptomService;

    private final DiagnosisService diagnosisService;

    private final PatientService patientService;

    public DiagnosisController(DiagnosisReasonerService diagnosisReasonerService,
                               MedicineService medicineService,
                               DiseaseService diseaseService,
                               SymptomService symptomService,
                               DiagnosisService diagnosisService,
                               PatientService patientService) {
        this.diagnosisReasonerService = diagnosisReasonerService;
        this.medicineService = medicineService;
        this.diseaseService = diseaseService;
        this.symptomService = symptomService;
        this.diagnosisService = diagnosisService;
        this.patientService = patientService;
    }

    @PostMapping("/diseases/suggest-diseases")
    public ResponseEntity diagnose(@RequestParam long patientId, @RequestBody List<Symptom> symptoms) {
        final Patient patient = patientService.findById(patientId);

        Set<Symptom> symptomSet = symptoms.stream()
                .map(symptom -> symptomService.findById(symptom.getId())).collect(Collectors.toSet());
        Set<Symptom> symptomsFirst = new HashSet<>();
        symptomsFirst.add(symptomService.findByName("Curenje iz nosa"));
        symptomsFirst.add(symptomService.findByName("Bol u grlu"));
        symptomsFirst.add(symptomService.findByName("Glavobolja"));
        symptomsFirst.add(symptomService.findByName("Kijanje"));
//        symptomsFirst.add(symptomService.findByName("Drhtavica"));
        symptomsFirst.add(symptomService.findByName("Kašalj"));

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

        List<Disease> diseases = diagnosisReasonerService.diagnose(patient, symptomSet);

        return ResponseEntity.ok(diseases);
    }

    @PostMapping("/diagnoses")
    public ResponseEntity create(@RequestBody @Valid DiagnosisCreateDto diagnosisCreateDto, @RequestParam long patientId) {

        final Patient patient = patientService.findById(patientId);

        Set<Medicine> medicines = diagnosisCreateDto.getMedicineIds().stream().
                map(medicineService::findById)
                .collect(Collectors.toSet());

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDate(new Date());
        diagnosis.setDisease(diseaseService.findById(diagnosisCreateDto.getDiseaseId()));
        diagnosis.setMedicines(medicines);
        diagnosis.setPatient(patient);

        diagnosis = diagnosisService.create(diagnosis);

        return ResponseEntity.created(URI.create(String.format("api/diagnoses/%d", diagnosis.getId()))).body(diagnosis);
    }

    @PostMapping("diagnoses/suggest-diseases")
    public ResponseEntity getSuggestedDiseases(@RequestBody IdListDto symptomIds) {
        symptomIds.getIds().add(symptomService.findByName("Kijanje").getId());
        final List<Symptom> symptoms = symptomIds.getIds().stream().map(symptomService::findById).collect(Collectors.toList());

        return ResponseEntity.ok(diagnosisReasonerService.getSortedDiseases(symptoms));
    }
}
