package com.aleknik.cdss.cdssservice.repository;

import com.aleknik.cdss.cdssservice.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
