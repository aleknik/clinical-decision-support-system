package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Medicine;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.service.DiagnosisReasonerService;
import com.aleknik.cdss.cdssservice.service.IngredientService;
import com.aleknik.cdss.cdssservice.service.MedicineService;
import com.aleknik.cdss.cdssservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MedicineController {

    private final MedicineService medicineService;
    private final IngredientService ingredientService;

    private final DiagnosisReasonerService diagnosisReasonerService;

    private final PatientService patientService;

    public MedicineController(MedicineService medicineService,
                              IngredientService ingredientService,
                              DiagnosisReasonerService diagnosisReasonerService,
                              PatientService patientService) {
        this.medicineService = medicineService;
        this.ingredientService = ingredientService;
        this.diagnosisReasonerService = diagnosisReasonerService;
        this.patientService = patientService;
    }

    @GetMapping("/medicines")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(medicineService.findAll());
    }

    @GetMapping("/medicines/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(medicineService.findById(id));
    }

    @PostMapping("/medicines")
    ResponseEntity create(@RequestBody Medicine medicineDto) {
        Medicine medicine = new Medicine();
        medicine.setName(medicineDto.getName());
        medicine.setMedicineType(medicineDto.getMedicineType());
        medicine.setIngredients(medicineDto.getIngredients().stream()
                .map(ingredient -> ingredientService.findById(ingredient.getId())).collect(Collectors.toSet()));

        medicine = medicineService.create(medicine);

        return ResponseEntity.created(URI.create("")).body(medicine);
    }

    @PostMapping("/medicines/check-allergies")
    ResponseEntity checkAllergies(@RequestBody List<Medicine> medicineListDto, @RequestParam long patientId) {

        Set<Medicine> medicines = medicineListDto.stream()
                .map(medicine -> medicineService.findById(medicine.getId())).collect(Collectors.toSet());

        final Patient patient = patientService.findById(patientId);

        return ResponseEntity.ok(diagnosisReasonerService.checkAllergies(medicines, patient));
    }
}
