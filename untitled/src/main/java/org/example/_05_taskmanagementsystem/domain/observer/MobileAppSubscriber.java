package org.example._05_taskmanagementsystem.domain.observer;

import org.example._05_taskmanagementsystem.domain.ChangeType;

public class MobileAppSubscriber implements TaskSubscriber{

    private String pushNotificationService;

    public MobileAppSubscriber(String pushNotificationService){
        this.pushNotificationService = pushNotificationService;
    }

    @Override
    public void update(int taskId, ChangeType changeType, String oldValue, String newValue) {
        // TODO: Implement push notification logic
        // Find out who subscribed to it, and send them..
        System.out.println("Push notification sent for task " + taskId + ": " + changeType + " - " + oldValue + " -> " + newValue);
    }

}
