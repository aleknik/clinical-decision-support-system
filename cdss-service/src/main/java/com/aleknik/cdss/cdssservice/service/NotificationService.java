package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.monitoring.Notification;
import com.aleknik.cdss.cdssservice.util.JsonUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notify(Notification notification, Patient patient) {
        notification.setTimeStamp(new Date());
        messagingTemplate.convertAndSend("/topic/" + patient.getId(), JsonUtil.json(notification));
    }
}
