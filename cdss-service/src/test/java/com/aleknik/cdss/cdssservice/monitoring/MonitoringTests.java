package com.aleknik.cdss.cdssservice.monitoring;

import com.aleknik.cdss.cdssservice.model.Diagnosis;
import com.aleknik.cdss.cdssservice.model.Disease;
import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.User;
import com.aleknik.cdss.cdssservice.model.monitoring.HeartBeat;
import com.aleknik.cdss.cdssservice.model.monitoring.Notification;
import com.aleknik.cdss.cdssservice.model.monitoring.OxygenLevel;
import com.aleknik.cdss.cdssservice.model.monitoring.UrineLevel;
import com.aleknik.cdss.cdssservice.service.NotificationService;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class MonitoringTests {

    private KieSession ksession;

    @Mock
    private NotificationService notificationService;

    @Before
    public void setup() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        ksession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        ksession.insert(notificationService);
    }

    @Test
    public void testOxygen() {
        // arrange
        SessionPseudoClock clock = ksession.getSessionClock();

        Patient patient = new Patient();
        ksession.insert(patient);

        OxygenLevel oxygenLevel = new OxygenLevel();
        oxygenLevel.setLevel(50);
        ksession.insert(oxygenLevel);
        clock.advanceTime(5, TimeUnit.MINUTES);
        oxygenLevel = new OxygenLevel();
        oxygenLevel.setLevel(45);
        ksession.insert(oxygenLevel);
        clock.advanceTime(10, TimeUnit.MINUTES);

        // act
        int ruleCount = ksession.fireAllRules();

        // assert
        Assert.assertEquals(1, ruleCount);
    }

    @Test
    public void testHeartBeat() {
        // arrange
        SessionPseudoClock clock = ksession.getSessionClock();

        Patient patient = new Patient();
        ksession.insert(patient);

        for (int i = 0; i < 30; i++) {
            ksession.insert(new HeartBeat());
            clock.advanceTime(300, TimeUnit.MILLISECONDS);
        }

        // act
        int ruleCount = ksession.fireAllRules();

        // assert
        Assert.assertEquals(1, ruleCount);
    }

    @Test
    public void needDialysisTest() {
        // arrange
        SessionPseudoClock clock = ksession.getSessionClock();

        Patient patient = new Patient();
        patient.setId(1);
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1);
        Disease disease = new Disease();
        disease.setId(1);
        disease.setName("Hronična bubrežna bolest");
        diagnosis.setDisease(disease);
        patient.getDiagnoses().add(diagnosis);
        ksession.insert(patient);

        UrineLevel urineLevel = new UrineLevel();
        urineLevel.setLevel(50);
        ksession.insert(urineLevel);

        for (int i = 0; i < 30; i++) {
            ksession.insert(new HeartBeat());
            clock.advanceTime(300, TimeUnit.MILLISECONDS);
        }

        // act
        int ruleCount = ksession.fireAllRules();

        // assert
        Assert.assertEquals(1, ruleCount);
    }
}
