package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.service.DiseaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/diseases")
    ResponseEntity findAll() {
        return ResponseEntity.ok(diseaseService.findAll());
    }
}
