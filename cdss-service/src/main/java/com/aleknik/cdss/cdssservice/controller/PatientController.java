package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.dto.PatientCreateDto;
import com.aleknik.cdss.cdssservice.model.dto.PatientUpdateDto;
import com.aleknik.cdss.cdssservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patients")
    public ResponseEntity create(@RequestBody @Valid PatientCreateDto patientCreateDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientCreateDto.getFirstName());
        patient.setLastName(patientCreateDto.getLastName());
        // todo: allergies

        patient = patientService.create(patient);

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
