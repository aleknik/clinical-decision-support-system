package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.Ingredient;
import com.aleknik.cdss.cdssservice.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new NotFoundException("Ingredient not found"));
    }

    public Ingredient create(Ingredient ingredient) {
        Ingredient newIngredient = new Ingredient();
        ingredient.setName(ingredient.getName());
        return ingredientRepository.save(newIngredient);
    }
}
