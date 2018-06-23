package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Optional<Object> findByName(String name);
}
