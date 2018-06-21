package com.aleknik.cdss.cdssservice;

import com.aleknik.cdss.cdssservice.model.*;
import com.aleknik.cdss.cdssservice.repository.*;
import com.aleknik.cdss.cdssservice.security.RoleConstants;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final SymptomRepository symptomRepository;

    private final DiseaseRepository diseaseRepository;

    private final PatientRepository patientRepository;

    private final MedicineRepository medicineRepository;

    private final DiagnosisRepository diagnosisRepository;

    private final IngredientRepository ingredientRepository;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder,
                      SymptomRepository symptomRepository,
                      DiseaseRepository diseaseRepository,
                      PatientRepository patientRepository,
                      MedicineRepository medicineRepository,
                      DiagnosisRepository diagnosisRepository,
                      IngredientRepository ingredientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.symptomRepository = symptomRepository;
        this.diseaseRepository = diseaseRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void run(ApplicationArguments args) {

        // Users
        initializeUser("admin", "admin", RoleConstants.ADMIN);
        initializeUser("doctor", "doctor", RoleConstants.DOCTOR);

        // Symptoms
        Symptom symptom1 = new Symptom("Curenje iz nosa", SymptomType.SIMPLE);
        symptomRepository.save(symptom1);
        Symptom symptom2 = new Symptom("Bol u grlu", SymptomType.SIMPLE);
        symptomRepository.save(symptom2);
        Symptom symptom3 = new Symptom("Glavobolja", SymptomType.SIMPLE);
        symptomRepository.save(symptom3);
        Symptom symptom4 = new Symptom("Kijanje", SymptomType.SIMPLE);
        symptomRepository.save(symptom4);
        Symptom symptom5 = new Symptom("Kašalj", SymptomType.SIMPLE);
        symptomRepository.save(symptom5);
        Symptom symptom6 = new Symptom("Temperatura veća od 38 ⁰C", SymptomType.SIMPLE);
        symptomRepository.save(symptom6);
        Symptom symptom7 = new Symptom("Drhtavica", SymptomType.SIMPLE);
        symptomRepository.save(symptom7);
        Symptom symptom8 = new Symptom("Bol koji se širi do ušiju", SymptomType.SIMPLE);
        symptomRepository.save(symptom8);
        Symptom symptom9 = new Symptom("Temperatura od 40 ⁰C do 41 ⁰C", SymptomType.SIMPLE);
        symptomRepository.save(symptom9);
        Symptom symptom10 = new Symptom("Gubitak apetita", SymptomType.SIMPLE);
        symptomRepository.save(symptom10);
        Symptom symptom11 = new Symptom("Umor", SymptomType.SIMPLE);
        symptomRepository.save(symptom11);
        Symptom symptom12 = new Symptom("Žuti sekret iz nosa", SymptomType.SIMPLE);
        symptomRepository.save(symptom12);
        Symptom symptom13 = new Symptom("Oticanje oko očiju", SymptomType.SIMPLE);
        symptomRepository.save(symptom13);
        Symptom symptom14 = new Symptom("Bolovao od prehlade ili groznice u poslednjih 60 dana", SymptomType.COMPLEX);
        symptomRepository.save(symptom14);
        Symptom symptom15 = new Symptom("6 meseci 10 slučajeva visok pritisak", SymptomType.COMPLEX);
        symptomRepository.save(symptom15);
        Symptom symptom16 = new Symptom("Često uriniranje", SymptomType.SIMPLE);
        symptomRepository.save(symptom16);
        Symptom symptom17 = new Symptom("Gubitak telesne težine", SymptomType.SIMPLE);
        symptomRepository.save(symptom17);
        Symptom symptom18 = new Symptom("Zamor", SymptomType.SIMPLE);
        symptomRepository.save(symptom18);
        Symptom symptom19 = new Symptom("Mučnina i povraćanje", SymptomType.SIMPLE);
        symptomRepository.save(symptom19);
        Symptom symptom20 = new Symptom("Nocturia", SymptomType.SIMPLE);
        symptomRepository.save(symptom20);
        Symptom symptom21 = new Symptom("Otoci nogu i zglobova", SymptomType.SIMPLE);
        symptomRepository.save(symptom21);
        Symptom symptom22 = new Symptom("Gušenje", SymptomType.SIMPLE);
        symptomRepository.save(symptom22);
        Symptom symptom23 = new Symptom("Bol u grudima", SymptomType.SIMPLE);
        symptomRepository.save(symptom23);
        Symptom symptom24 = new Symptom("Pacijent boluje od hipertenzije više od 6 meseci", SymptomType.COMPLEX);
        symptomRepository.save(symptom24);
        Symptom symptom25 = new Symptom("Pacijent boluje od dijabetesa", SymptomType.COMPLEX);
        symptomRepository.save(symptom25);
        Symptom symptom26 = new Symptom("Oporavlja se od operacije", SymptomType.SIMPLE);
        symptomRepository.save(symptom26);
        Symptom symptom27 = new Symptom("Dijareja", SymptomType.SIMPLE);
        symptomRepository.save(symptom27);
        Symptom symptom28 = new Symptom("U poslednjih 14 dana dijagnostikovana bolest koja kao simptom ima povišenu telesnu temperaturu", SymptomType.COMPLEX);
        symptomRepository.save(symptom28);
        Symptom symptom29 = new Symptom("U poslednjih 21 dana dijagnostikovana bolest za koju je primao antibiotike", SymptomType.COMPLEX);
        symptomRepository.save(symptom29);

        // Disease
        // First
        Disease disease1 = new Disease("Prehlada", DiseaseGroup.FIRST);
        disease1.getGeneralSymptoms().add(symptom1);
        disease1.getGeneralSymptoms().add(symptom2);
        disease1.getGeneralSymptoms().add(symptom3);
        disease1.getGeneralSymptoms().add(symptom4);
        disease1.getGeneralSymptoms().add(symptom5);
        diseaseRepository.save(disease1);

        Disease disease2 = new Disease("Groznica", DiseaseGroup.FIRST);
        disease2.getGeneralSymptoms().add(symptom4);
        disease2.getGeneralSymptoms().add(symptom2);
        disease2.getGeneralSymptoms().add(symptom5);
        disease2.getGeneralSymptoms().add(symptom6);
        disease2.getGeneralSymptoms().add(symptom1);
        disease2.getGeneralSymptoms().add(symptom3);
        disease2.getGeneralSymptoms().add(symptom7);
        diseaseRepository.save(disease2);

        Disease disease3 = new Disease("Upala krajnika", DiseaseGroup.FIRST);
        disease3.getGeneralSymptoms().add(symptom2);
        disease3.getGeneralSymptoms().add(symptom8);
        disease3.getGeneralSymptoms().add(symptom3);
        disease3.getGeneralSymptoms().add(symptom9);
        disease3.getGeneralSymptoms().add(symptom7);
        disease3.getGeneralSymptoms().add(symptom10);
        disease3.getGeneralSymptoms().add(symptom11);
        disease3.getGeneralSymptoms().add(symptom12);
        diseaseRepository.save(disease3);

        Disease disease4 = new Disease("Sinusna infekcija", DiseaseGroup.FIRST);
        disease4.getGeneralSymptoms().add(symptom13);
        disease4.getGeneralSymptoms().add(symptom3);
        disease4.getGeneralSymptoms().add(symptom12);
        disease4.getGeneralSymptoms().add(symptom2);
        disease4.getGeneralSymptoms().add(symptom6);
        disease4.getGeneralSymptoms().add(symptom5);
        disease4.getSpecificSymptoms().add(symptom14);
        diseaseRepository.save(disease4);

        // Second
        Disease disease = new Disease("Hipertenzija", DiseaseGroup.SECOND);
        disease.getGeneralSymptoms().add(symptom15);
        diseaseRepository.save(disease);

        disease = new Disease("Dijabetes", DiseaseGroup.SECOND);
        disease.getGeneralSymptoms().add(symptom16);
        disease.getGeneralSymptoms().add(symptom17);
        disease.getGeneralSymptoms().add(symptom18);
        disease.getGeneralSymptoms().add(symptom19);
        diseaseRepository.save(disease);

        // Third
        disease = new Disease("Hronična bubrežna bolest", DiseaseGroup.THIRD);
        disease.getGeneralSymptoms().add(symptom18);
        disease.getGeneralSymptoms().add(symptom20);
        disease.getGeneralSymptoms().add(symptom21);
        disease.getGeneralSymptoms().add(symptom22);
        disease.getGeneralSymptoms().add(symptom23);
        disease.getSpecificSymptoms().add(symptom24);
        disease.getSpecificSymptoms().add(symptom25);
        diseaseRepository.save(disease);

        disease = new Disease("Akutna bubrežna povreda", DiseaseGroup.THIRD);
        disease.getSpecificSymptoms().add(symptom26);
        disease.getGeneralSymptoms().add(symptom18);
        disease.getGeneralSymptoms().add(symptom22);
        disease.getGeneralSymptoms().add(symptom21);
        disease.getGeneralSymptoms().add(symptom27);
        disease.getSpecificSymptoms().add(symptom28);
        disease.getSpecificSymptoms().add(symptom29);
        diseaseRepository.save(disease);

        // other
        disease = new Disease("Visok pritisak", DiseaseGroup.FIRST);
        diseaseRepository.save(disease);

        Patient patient = new Patient();
        patient.setFirstName("pera");
        patient.setLastName("mile");
        patientRepository.save(patient);

        Ingredient ingredient = new Ingredient();
        ingredient.setName("TestIng");
        ingredientRepository.save(ingredient);

        Medicine medicine1 = new Medicine();
        medicine1.setName("testmedicne");
        medicine1.setMedicineType(MedicineType.ANTIBIOTIC);
        medicine1.getIngredients().add(ingredient);
        medicineRepository.save(medicine1);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDate(new Date());
        diagnosis.setPatient(patient);
        diagnosis.setDisease(diseaseRepository.findByName("Prehlada").get());
        diagnosis.getMedicines().add(medicine1);
        diagnosisRepository.save(diagnosis);

        patient = new Patient();
        patient.setFirstName("peraa");
        patient.setLastName("milea");
        patientRepository.save(patient);
    }

    private void initializeUser(String username, String password, String roleName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        final List<Role> roles = new ArrayList<>();
        final Role role = new Role(roleName);
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
    }
}
