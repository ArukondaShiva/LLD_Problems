package org.example._06_pubsubsystem.service;

import org.example._06_pubsubsystem.domain.Subscriber;
import org.example._06_pubsubsystem.domain.Subscription;
import org.example._06_pubsubsystem.domain.observer.RealtimeSubscriber;
import org.example._06_pubsubsystem.domain.observer.SubscriberObserver;
import org.example._06_pubsubsystem.repository.MessageDeliveryRepository;
import org.example._06_pubsubsystem.repository.SubscriberRepository;
import org.example._06_pubsubsystem.repository.SubscriptionRepository;
import org.example._06_pubsubsystem.repository.TopicRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SubscriberService {

    private SubscriberRepository subscriberRepository;
    private SubscriptionRepository subscriptionRepository;
    private MessageDeliveryRepository messageDeliveryRepository;
    private TopicRepository topicRepository;




    public SubscriberService(SubscriberRepository subscriberRepository,
                             SubscriptionRepository subscriptionRepository,
                             MessageDeliveryRepository messageDeliveryRepository,
                             TopicRepository topicRepository) {
        this.subscriberRepository = subscriberRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.messageDeliveryRepository = messageDeliveryRepository;
        this.topicRepository = topicRepository;
    }


    public Subscriber registerSubscriber(String email){

        Subscriber subscriber = new Subscriber(UUID.randomUUID().toString(),email,null,true,System.currentTimeMillis(),System.currentTimeMillis());
        return subscriberRepository.save(subscriber);
    }

    public void goOnline(String subscriberId,String connectionId){
        // TODO: Add socket connection as well
        subscriberRepository.updateOnlineStatus(subscriberId,true,connectionId);

        // Add to realtime lists for all active subscriptions
        List<Subscription> subscriptions = subscriptionRepository.findBySubscriber(subscriberId);

        for(Subscription subscription : subscriptions){
            topicRepository.findById(subscription.getTopicId()).ifPresent(topic->{
                SubscriberObserver realtimeSub = new RealtimeSubscriber(connectionId, subscriberId);
               topic.getMessageSubject().addRealtimeSubscriber(realtimeSub);
            });
        }

        // TOTO : push pending deliveries
        pushPendingDeliveries(subscriberId);
    }



    public void goOffline(String subscriberId){

        subscriberRepository.updateOnlineStatus(subscriberId,false,null);

        // Remove from realtime lists for all active subscriptions
        List<Subscription> subscriptions = subscriptionRepository.findBySubscriber(subscriberId);

        for(Subscription subscription : subscriptions){
            topicRepository.findById(subscription.getTopicId()).ifPresent(topic->{
                // TODO: Remove specific realtime subscriber
                topic.getMessageSubject().removeRealtimeSubscriber(null);
            });
        }
    }



    private void pushPendingDeliveries(String subscriberId) {
        // TODO: Implement pending delivery logic
        // Step-1 find all the topics he is subscribed to
        // Step-2: for all these topics, find all the messages that are PENDING
        // STEP-3: Sending out the messages...
        System.out.println("Pushing pending deliveries for subscriber: " + subscriberId);
    }


}
