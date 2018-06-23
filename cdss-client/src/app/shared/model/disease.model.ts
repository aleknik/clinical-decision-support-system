import { Symptom } from './symptom.model';

export class Disease {
    id: number;

    name: string;

    diseaseGroup: string;

    specificSymptoms: Symptom[];

    generalSymptoms: Symptom[];
}
