package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.model.Patient;
import com.aleknik.cdss.cdssservice.model.monitoring.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    public void notify(Notification notification, Patient patient) {
        System.out.println(notification.getMessage());
    }
}
