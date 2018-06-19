package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.*;
import com.aleknik.cdss.cdssservice.model.dto.DiagnosisCreateDto;
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

    public DiagnosisController(DiagnosisReasonerService diagnosisReasonerService,
                               MedicineService medicineService,
                               DiseaseService diseaseService,
                               SymptomService symptomService, DiagnosisService diagnosisService) {
        this.diagnosisReasonerService = diagnosisReasonerService;
        this.medicineService = medicineService;
        this.diseaseService = diseaseService;
        this.symptomService = symptomService;
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/{userId}/suggest-diagnose")
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


    @PostMapping("{userId}/diagnoses")
    public ResponseEntity create(@RequestBody @Valid DiagnosisCreateDto diagnosisCreateDto, @PathVariable long userId) {

        Set<Disease> diseases = diagnosisCreateDto.getDiseaseIds().stream().
                map(diseaseService::findById)
                .collect(Collectors.toSet());

        Set<Medicine> medicines = diagnosisCreateDto.getMedicineIds().stream().
                map(medicineService::findById)
                .collect(Collectors.toSet());

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDate(new Date());
        diagnosis.setDiseases(diseases);
        diagnosis.setMedicines(medicines);

        diagnosis = diagnosisService.create(diagnosis);

        return ResponseEntity.created(URI.create(String.format("api/%d/diagnoses/%d", userId, diagnosis.getId()))).body(diagnosis);
    }
}
