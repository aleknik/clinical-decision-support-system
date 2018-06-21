package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicines")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(medicineService.findAll());
    }
}
