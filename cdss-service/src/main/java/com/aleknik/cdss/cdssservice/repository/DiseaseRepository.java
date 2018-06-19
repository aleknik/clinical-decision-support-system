package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Optional<Disease> findByName(String name);
}
