package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.BadRequestException;
import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Medicine;
import com.aleknik.cdss.cdssservice.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public Medicine findById(long id) {
        return medicineRepository.findById(id).orElseThrow(() -> new NotFoundException("Medicine not found"));
    }

    public Medicine create(Medicine medicine) {

        if (medicineRepository.findByName(medicine.getName()).isPresent()) {
            throw new BadRequestException(String.format("Medicine '%s' already exists", medicine.getName()));
        }

        return medicineRepository.save(medicine);
    }

    public void delete(long id) {
        final Medicine medicine = findById(id);

        medicineRepository.delete(medicine);
    }

    public Medicine update(Medicine updatedMedicine, long id) {
        final Medicine medicine = findById(id);

        if (!updatedMedicine.getName().equals(medicine.getName()) &&
                medicineRepository.findByName(updatedMedicine.getName()).isPresent()) {
            throw new BadRequestException(String.format("Medicine '%s' already exists", updatedMedicine.getName()));
        }

        if (!updatedMedicine.getName().equals(medicine.getName())) {
            medicine.setName(updatedMedicine.getName());
        }

        medicine.setMedicineType(updatedMedicine.getMedicineType());

        medicine.setIngredients(updatedMedicine.getIngredients());

        return medicineRepository.save(medicine);
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }
}
