package com.aleknik.cdss.cdssservice;

import com.aleknik.cdss.cdssservice.model.*;
import com.aleknik.cdss.cdssservice.repository.DiseaseRepository;
import com.aleknik.cdss.cdssservice.repository.SymptomRepository;
import com.aleknik.cdss.cdssservice.repository.UserRepository;
import com.aleknik.cdss.cdssservice.security.RoleConstants;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final SymptomRepository symptomRepository;

    private final DiseaseRepository diseaseRepository;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder,
                      SymptomRepository symptomRepository,
                      DiseaseRepository diseaseRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.symptomRepository = symptomRepository;
        this.diseaseRepository = diseaseRepository;
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
        Symptom symptom14 = new Symptom("Bolovao od prehlade ili groznice u poslednjih 60 dana.", SymptomType.COMPLEX);
        symptomRepository.save(symptom14);

        // Disease
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
