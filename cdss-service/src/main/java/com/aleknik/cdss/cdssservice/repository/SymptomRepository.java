package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
