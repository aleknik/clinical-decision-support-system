package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.model.Medicine;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.model.dto.Allergies;
import com.aleknik.cdss.cdssservice.model.dto.DiagnosisResultDto;
import com.aleknik.cdss.cdssservice.model.dto.SymptomListDto;
import com.aleknik.cdss.cdssservice.repository.DiseaseRepository;
import com.aleknik.cdss.cdssservice.util.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiagnosisReasonerService {

    private final KieContainer kieContainer;

    private final DiseaseRepository diseaseRepository;

    private final SymptomService symptomService;

    public DiagnosisReasonerService(KieContainer kieContainer,
                                    DiseaseRepository diseaseRepository,
                                    SymptomService symptomService) {
        this.kieContainer = kieContainer;
        this.diseaseRepository = diseaseRepository;
        this.symptomService = symptomService;
    }

    public List<Disease> diagnose(Patient patient, Set<Symptom> symptoms) {
        final KieSession kieSession = kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());

        final SymptomListDto symptomListDto = new SymptomListDto();
        symptomListDto.setSymptoms(symptoms);
        final DiagnosisResultDto result = new DiagnosisResultDto();

        patient.getDiagnoses().forEach(kieSession::insert);
        diseaseRepository.findAll().forEach(kieSession::insert);
        kieSession.insert(patient);
        kieSession.insert(result);
        kieSession.insert(symptomListDto);
        kieSession.insert(symptomService);

        kieSession.fireAllRules();

        kieSession.dispose();

        return result.getDiseases();
    }

    public List<Disease> getSortedDiseases(Set<Symptom> symptoms) {
        final KieSession kieSession = kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());

        diseaseRepository.findAll().forEach(kieSession::insert);
        SymptomListDto symptomListDto = new SymptomListDto();
        symptomListDto.setSymptoms(symptoms);
        kieSession.insert(symptomListDto);

        QueryResults results = kieSession.getQueryResults("Get diseases");

        List<Disease> sorted = new ArrayList<>();
        for (QueryResultsRow result : results) {
            sorted.add((Disease) result.get("$disease"));
        }

        sorted.sort((o1, o2) -> {
            HashSet<Symptom> intersection1 = new HashSet<>(symptoms);
            intersection1.retainAll(o1.getAllSymptoms());

            HashSet<Symptom> intersection2 = new HashSet<>(symptoms);
            intersection2.retainAll(o2.getAllSymptoms());

            return intersection2.size() - intersection1.size();
        });

        kieSession.dispose();

        return sorted;
    }

    public List<Symptom> getSortedSymptoms(Disease disease, Set<Symptom> symptoms) {
        final KieSession kieSession = kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());

        diseaseRepository.findAll().forEach(kieSession::insert);
        SymptomListDto symptomListDto = new SymptomListDto();
        symptomListDto.setSymptoms(symptoms);
        kieSession.insert(symptomListDto);

        QueryResults results = kieSession.getQueryResults("Get symptoms", disease.getName());

        List<Symptom> sorted = new ArrayList<>();
        for (QueryResultsRow result : results) {
            sorted.addAll((Collection<? extends Symptom>) result.get("$foundSpecific"));
        }

        for (QueryResultsRow result : results) {
            sorted.addAll((Collection<? extends Symptom>) result.get("$foundGeneral"));
        }

        kieSession.dispose();

        return sorted;
    }

    public Set<Medicine> checkAllergies(Set<Medicine> medicines, Patient patient) {
        final KieSession kieSession = kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());

        Allergies allergies = new Allergies();
        kieSession.insert(allergies);
        medicines.forEach(kieSession::insert);
        kieSession.insert(patient);

        kieSession.fireAllRules();

        kieSession.dispose();

        return allergies.getMedicines();
    }
}
