package org.example._06_pubsubsystem.controller;

import org.example._06_pubsubsystem.domain.Subscriber;
import org.example._06_pubsubsystem.service.SubscriberService;

public class SubscriberController {


    private SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService){
        this.subscriberService = subscriberService;
    }

    public Subscriber registerSubscriber(String email) {
        return subscriberService.registerSubscriber(email);
    }

    public void goOnline(String subscriberId, String connectionId) {
        subscriberService.goOnline(subscriberId, connectionId);
    }

    public void goOffline(String subscriberId) {
        subscriberService.goOffline(subscriberId);
    }

}
