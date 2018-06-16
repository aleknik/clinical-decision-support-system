package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
