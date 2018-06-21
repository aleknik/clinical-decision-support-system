package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.SymptomType;
import com.aleknik.cdss.cdssservice.service.SymptomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SymptomController {

    private final SymptomService symptomService;

    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @GetMapping("/symptoms")
    public ResponseEntity findAllSimple() {
        return ResponseEntity.ok(symptomService.findByType(SymptomType.SIMPLE));
    }
}
