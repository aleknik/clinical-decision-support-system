package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.dto.PatientCreateDto;
import com.aleknik.cdss.cdssservice.model.dto.PatientUpdateDto;
import com.aleknik.cdss.cdssservice.service.IngredientService;
import com.aleknik.cdss.cdssservice.service.MedicineService;
import com.aleknik.cdss.cdssservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    private final MedicineService medicineService;

    private final IngredientService ingredientService;

    public PatientController(PatientService patientService,
                             MedicineService medicineService,
                             IngredientService ingredientService) {
        this.patientService = patientService;
        this.medicineService = medicineService;
        this.ingredientService = ingredientService;
    }

    @PostMapping("/patients")
    public ResponseEntity create(@RequestBody @Valid PatientCreateDto patientCreateDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientCreateDto.getFirstName());
        patient.setLastName(patientCreateDto.getLastName());

        patient.setMedicineAllergies(patientCreateDto.getMedicineAllergies().stream().
                map(medicine -> medicineService.findById(medicine.getId())).collect(Collectors.toSet()));

        patient.setIngredientAllergies(patientCreateDto.getIngredientAllergies().stream().
                map(ingredient -> ingredientService.findById(ingredient.getId())).collect(Collectors.toSet()));

        patientService.create(patient);

        return ResponseEntity.created(URI.create(String.format("api/patients/%d", patient.getId()))).body(patient);
    }

    @GetMapping("/patients")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity update(@RequestBody PatientUpdateDto patientUpdateDto, @PathVariable long id) {
        final Patient patient = new Patient();
        patient.setFirstName(patientUpdateDto.getFirstName());
        patient.setLastName(patientUpdateDto.getLastName());

        return ResponseEntity.ok(patientService.update(patient, id));
    }
}
