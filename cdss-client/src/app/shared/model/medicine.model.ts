import { Ingredient } from './ingredient.model';

export class Medicine {
    id: number;
    name: string;

    medicineType: string;

    ingredients: Ingredient[];

    isAllergic: boolean;
}
