package com.aleknik.cdss.cdssservice.task;

import com.aleknik.cdss.cdssservice.model.monitoring.HeartBeat;
import com.aleknik.cdss.cdssservice.model.monitoring.Notification;
import com.aleknik.cdss.cdssservice.model.monitoring.OxygenLevel;
import com.aleknik.cdss.cdssservice.service.NotificationService;
import com.aleknik.cdss.cdssservice.service.PatientService;
import com.aleknik.cdss.cdssservice.util.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitoringTask {

    private final KieContainer kieContainer;

    private final NotificationService notificationService;

    private final PatientService patientService;

    private KieSession kieSession;

    public MonitoringTask(KieContainer kieContainer, NotificationService notificationService, PatientService patientService) {
        this.kieContainer = kieContainer;
        this.notificationService = notificationService;
        this.patientService = patientService;

        kieSession = this.kieContainer.newKieSession("cdssSession");
        kieSession.addEventListener(new DebugAgendaEventListener());
        kieSession.insert(notificationService);
        kieSession.insert(patientService.findAll().get(0));

    }

    @Scheduled(fixedRate = 100)
    public void InsertIntoSession() {
        OxygenLevel oxygenLevel = new OxygenLevel();
        oxygenLevel.setLevel(50);
        kieSession.insert(oxygenLevel);

        kieSession.insert(new HeartBeat());
    }

    @Scheduled(fixedRate = 1000)
    public void fireRules() {
        kieSession.fireAllRules();
    }
}
