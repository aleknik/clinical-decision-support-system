package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.*;
import com.aleknik.cdss.cdssservice.model.dto.DiagnosisCreateDto;
import com.aleknik.cdss.cdssservice.security.RoleConstants;
import com.aleknik.cdss.cdssservice.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final UserService userService;

    public DiagnosisController(DiagnosisReasonerService diagnosisReasonerService,
                               MedicineService medicineService,
                               DiseaseService diseaseService,
                               SymptomService symptomService,
                               DiagnosisService diagnosisService,
                               PatientService patientService,
                               UserService userService) {
        this.diagnosisReasonerService = diagnosisReasonerService;
        this.medicineService = medicineService;
        this.diseaseService = diseaseService;
        this.symptomService = symptomService;
        this.diagnosisService = diagnosisService;
        this.patientService = patientService;
        this.userService = userService;
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
    @PreAuthorize("hasAuthority('" + RoleConstants.DOCTOR + "')")
    public ResponseEntity create(@RequestBody @Valid DiagnosisCreateDto diagnosisCreateDto, @RequestParam long patientId) {

        final Patient patient = patientService.findById(patientId);

        Set<Medicine> medicines = diagnosisCreateDto.getMedicines().stream().
                map(medicine -> medicineService.findById(medicine.getId()))
                .collect(Collectors.toSet());

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDate(new Date());
        diagnosis.setDisease(diseaseService.findById(diagnosisCreateDto.getDisease().getId()));
        diagnosis.setMedicines(medicines);
        diagnosis.setPatient(patient);
        diagnosis.setDoctor(userService.findCurrentUser());

        diagnosis = diagnosisService.create(diagnosis);

        return ResponseEntity.created(URI.create(String.format("api/diagnoses/%d", diagnosis.getId()))).body(diagnosis);
    }

    @PostMapping("diagnoses/diseases")
    @PreAuthorize("hasAuthority('" + RoleConstants.DOCTOR + "')")
    public ResponseEntity getSuggestedDiseases(@RequestBody List<Symptom> symptomList) {
        final Set<Symptom> symptoms = symptomList.stream()
                .map(symptom -> symptomService.findById(symptom.getId())).collect(Collectors.toSet());

        return ResponseEntity.ok(diagnosisReasonerService.getSortedDiseases(symptoms));
    }

    @GetMapping("diagnoses/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.DOCTOR + "')")
    ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(diagnosisService.findById(id));
    }

    @GetMapping("/diagnoses")
    @PreAuthorize("hasAuthority('" + RoleConstants.DOCTOR + "')")
    ResponseEntity getDaignosesForPatients(@RequestParam long patientId) {
        return ResponseEntity.ok(diagnosisService.findByPatient(patientId));
    }
}
