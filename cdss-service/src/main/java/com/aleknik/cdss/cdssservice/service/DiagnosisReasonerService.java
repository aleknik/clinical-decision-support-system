package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.Symptom;
import com.aleknik.cdss.cdssservice.model.dto.DiagnosisResultDto;
import com.aleknik.cdss.cdssservice.model.dto.SymptomListDto;
import com.aleknik.cdss.cdssservice.repository.DiseaseRepository;
import com.aleknik.cdss.cdssservice.util.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DiagnosisReasonerService {

    private final KieContainer kieContainer;

    private DiseaseRepository diseaseRepository;

    public DiagnosisReasonerService(KieContainer kieContainer, DiseaseRepository diseaseRepository) {
        this.kieContainer = kieContainer;
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> diagnose(Patient patient, Set<Symptom> symptoms) {
        final KieSession kieSession = kieContainer.newKieSession();
        kieSession.addEventListener(new DebugAgendaEventListener());

        final SymptomListDto symptomListDto = new SymptomListDto();
        symptomListDto.setSymptoms(symptoms);
        final DiagnosisResultDto result = new DiagnosisResultDto();

        diseaseRepository.findAll().forEach(kieSession::insert);
        kieSession.insert(patient);
        kieSession.insert(result);
        kieSession.insert(symptomListDto);

        kieSession.fireAllRules();

        return result.getDiseases();
    }
}
