package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
