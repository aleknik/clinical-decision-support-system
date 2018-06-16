package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
