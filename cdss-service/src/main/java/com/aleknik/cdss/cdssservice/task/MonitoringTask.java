package com.aleknik.cdss.cdssservice.task;

import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.monitoring.HeartBeat;
import com.aleknik.cdss.cdssservice.model.monitoring.OxygenLevel;
import com.aleknik.cdss.cdssservice.model.monitoring.UrineLevel;
import com.aleknik.cdss.cdssservice.service.NotificationService;
import com.aleknik.cdss.cdssservice.service.PatientService;
import com.aleknik.cdss.cdssservice.util.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonitoringTask {

    private final KieContainer kieContainer;

    private final NotificationService notificationService;

    private final PatientService patientService;

    private final List<FactHandle> patientHandles = new ArrayList<>();

    private KieSession kieSession;

    public MonitoringTask(KieContainer kieContainer, NotificationService notificationService, PatientService patientService) {
        this.kieContainer = kieContainer;
        this.notificationService = notificationService;
        this.patientService = patientService;

        kieSession = this.kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());
        kieSession.insert(notificationService);
    }

    @Scheduled(fixedDelay = 10000)
    public void init() {
        patientHandles.forEach(factHandle -> kieSession.delete(factHandle));
        patientHandles.clear();

        patientService.findAll().forEach(patient -> patientHandles.add(kieSession.insert(patient)));
    }

    @Scheduled(fixedDelay = 200)
    public void InsertIntoSession() {

        patientHandles.forEach(factHandle -> {
            Patient patient = (Patient) kieSession.getObject(factHandle);
            OxygenLevel oxygenLevel = new OxygenLevel();
            oxygenLevel.setLevel(50);
            oxygenLevel.setPatient(patient);
            kieSession.insert(oxygenLevel);

            HeartBeat heartBeat = new HeartBeat();
            heartBeat.setPatient(patient);
            kieSession.insert(heartBeat);

            UrineLevel urineLevel = new UrineLevel();
            urineLevel.setPatient(patient);
            urineLevel.setLevel(0);
            kieSession.insert(urineLevel);
        });
    }

    @Scheduled(fixedRate = 5000)
    public void fireRules() {
        kieSession.getAgenda().getAgendaGroup("Monitoring").setFocus();
        kieSession.fireAllRules();
    }
}
