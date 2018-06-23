package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Ingredient;
import com.aleknik.cdss.cdssservice.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    ResponseEntity findAll() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @PostMapping("/ingredients")
    ResponseEntity create(@RequestBody Ingredient ingredient) {

        final Ingredient created = ingredientService.create(ingredient);

        return ResponseEntity.created(URI.create("")).body(created);
    }

    @GetMapping("/ingredients/{id}")
    ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PutMapping("/ingredients/{id}")
    ResponseEntity findById(@PathVariable long id, @RequestBody Ingredient updated) {
        ingredientService.findById(id);
        return ResponseEntity.ok(ingredientService.update(updated, id));
    }
}
