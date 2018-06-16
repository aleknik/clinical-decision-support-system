package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Optional<Symptom> findByName(String name);
}
