import { Disease } from './disease.model';
import { Medicine } from './medicine.model';
import { Patient } from './patient.model';
import { User } from './user.model';

export class Diagnosis {
    id: number;

    disease: Disease;

    medicines: Medicine[];

    date: Date;

    patient: Patient;

    doctor: User;
}
