import { Medicine } from './medicine.model';
import { Ingredient } from './ingredient.model';

export class Patient {

    id: number;

    firstName: string;

    lastName: string;

    medicineAllergies: Medicine[];

    ingredientAllergies: Ingredient[];
}
