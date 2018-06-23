package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.BadRequestException;
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

        if (ingredientRepository.findByName(ingredient.getName()).isPresent()) {
            throw new BadRequestException(String.format("Ingredient '%s' already exists", ingredient.getName()));
        }

        return ingredientRepository.save(newIngredient);
    }

    public Ingredient update(Ingredient ingredient, long id) {
        Ingredient ingredientCurr = findById(id);
        if (!ingredient.getName().equals(ingredientCurr.getName()) &&
                ingredientRepository.findByName(ingredient.getName()).isPresent()) {
            throw new BadRequestException(String.format("Ingredient '%s' already exists", ingredient.getName()));
        }

        ingredientCurr.setName(ingredient.getName());

        return ingredientRepository.save(ingredientCurr);
    }
}
