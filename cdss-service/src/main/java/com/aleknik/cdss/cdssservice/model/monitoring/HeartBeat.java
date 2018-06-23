package com.aleknik.cdss.cdssservice.model.monitoring;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15s")
public class HeartBeat {
}
