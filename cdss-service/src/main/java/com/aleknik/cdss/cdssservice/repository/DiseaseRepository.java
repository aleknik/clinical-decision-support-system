package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
