package com.aleknik.cdss.cdssservice.model.monitoring;

import java.util.Date;

public class Notification {

    private String message;

    private Date timeStamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
