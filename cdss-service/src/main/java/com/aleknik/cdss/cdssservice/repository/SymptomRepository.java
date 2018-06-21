package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.model.SymptomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Optional<Symptom> findByName(String name);

    List<Symptom> findBySymptomType(SymptomType symptomType);
}
