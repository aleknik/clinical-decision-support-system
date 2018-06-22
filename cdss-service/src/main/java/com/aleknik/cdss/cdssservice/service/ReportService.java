package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.model.dto.Report;
import com.aleknik.cdss.cdssservice.repository.DiagnosisRepository;
import com.aleknik.cdss.cdssservice.util.DebugAgendaEventListener;
import com.sun.org.apache.regexp.internal.RE;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final DiagnosisRepository diagnosisRepository;

    private final KieContainer kieContainer;

    public ReportService(DiagnosisRepository diagnosisRepository, KieContainer kieContainer) {
        this.diagnosisRepository = diagnosisRepository;
        this.kieContainer = kieContainer;
    }

    public Report getReport() {
        final KieSession kieSession = kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());

        Report report = new Report();
        kieSession.insert(report);
        diagnosisRepository.findAll().forEach(kieSession::insert);

        kieSession.fireAllRules();

        return report;

    }
}
