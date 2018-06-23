package com.aleknik.cdss.cdssservice.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieConfig {

    @Value("${rules.groupId}")
    private String groupId;

    @Value("${rules.artifactId}")
    private String artifactId;

    @Value("${rules.version}")
    private String version;

    @Bean
    public KieContainer kieContainer() {
        final KieServices kieServices = KieServices.Factory.get();

        final KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId(groupId, artifactId, version));
        final KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
        kieScanner.start(10000);

        return kieContainer;
    }


    @Bean
    public KieSession kieSession() {
        final KieServices kieServices = KieServices.Factory.get();

        final KieContainer kieContainer = kieServices.newKieContainer(kieServices.newReleaseId(groupId, artifactId, version));
        final KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
        kieScanner.start(10000);

        return kieContainer.newKieSession("cdssSession");
    }
}
