package com.aleknik.cdss.cdssservice.model.monitoring;

import com.aleknik.cdss.cdssservice.model.Patient;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15s")
public class HeartBeat {

    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
