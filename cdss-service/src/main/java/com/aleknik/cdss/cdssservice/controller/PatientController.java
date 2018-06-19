package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.dto.PatientCreateDto;
import com.aleknik.cdss.cdssservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
