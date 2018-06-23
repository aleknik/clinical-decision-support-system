package com.aleknik.cdss.cdssservice.model.dto;

import com.aleknik.cdss.cdssservice.model.Ingredient;
import com.aleknik.cdss.cdssservice.model.Medicine;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PatientCreateDto {

    @NotEmpty
    @NotNull
    private String firstName;

    @NotEmpty
    @NotNull
    private String lastName;

    private List<Medicine> medicineAllergies;

    private List<Ingredient> ingredientAllergies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Medicine> getMedicineAllergies() {
        return medicineAllergies;
    }

    public void setMedicineAllergies(List<Medicine> medicineAllergies) {
        this.medicineAllergies = medicineAllergies;
    }

    public List<Ingredient> getIngredientAllergies() {
        return ingredientAllergies;
    }

    public void setIngredientAllergies(List<Ingredient> ingredientAllergies) {
        this.ingredientAllergies = ingredientAllergies;
    }
}
