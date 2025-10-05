package org.example._06_pubsubsystem.domain.observer;


import org.example._06_pubsubsystem.domain.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageSubject {


    private List<SubscriberObserver> emailSubscribers;
    private List<SubscriberObserver> realTimeSubscribers;

    public MessageSubject(){
        this.emailSubscribers = new CopyOnWriteArrayList<>();
        this.realTimeSubscribers = new CopyOnWriteArrayList<>();
    }

    public void addEmailSubscriber(SubscriberObserver subscriber){
        emailSubscribers.add(subscriber);
    }

    public void removeEmailSubscriber(SubscriberObserver subscriber){
        emailSubscribers.remove(subscriber);
    }

    public void addRealtimeSubscriber(SubscriberObserver subscriber){
        realTimeSubscribers.add(subscriber);
    }

    public void removeRealtimeSubscriber(SubscriberObserver subscriber){
        realTimeSubscribers.remove(subscriber);
    }


    public void notify(Message message){
        notifyEmailSubscribers(message);
        notifyRealtimeSubscription(message);
    }

    public void notifyEmailSubscribers(Message message){
        for(SubscriberObserver subscriber : emailSubscribers){
            subscriber.update(message);
        }
    }

    public void notifyRealtimeSubscription(Message message){
        for(SubscriberObserver subscriber : realTimeSubscribers){
            subscriber.update(message);
        }
    }

    public List<SubscriberObserver> getEmailSubscribers(){
        return new ArrayList<>(emailSubscribers);
    }


    public List<SubscriberObserver> getRealtimeSubscribers(){
        return new ArrayList<>(realTimeSubscribers);
    }

}
