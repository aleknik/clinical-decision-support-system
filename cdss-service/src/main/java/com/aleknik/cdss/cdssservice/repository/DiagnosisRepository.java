package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    List<Diagnosis> findByPatientId(long id);
}
