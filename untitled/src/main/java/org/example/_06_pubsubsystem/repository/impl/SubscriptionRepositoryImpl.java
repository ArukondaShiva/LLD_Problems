package org.example._06_pubsubsystem.repository.impl;

import org.example._06_pubsubsystem.domain.Subscription;
import org.example._06_pubsubsystem.repository.SubscriptionRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private Map<String,Subscription> subscriptions = new ConcurrentHashMap<>();

    @Override
    public Subscription save(Subscription subscription) {
        subscriptions.put(subscription.getId(),subscription);
        return subscription;
    }

    @Override
    public List<Subscription> findByTopic(String topicId) {

        return subscriptions.values().stream()
                .filter(sub->sub.getTopicId().equals(topicId) && sub.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> findBySubscriber(String subscriberId) {

        return subscriptions.values().stream()
                .filter(sub->sub.getSubscriberId().equals(subscriberId) && sub.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateSubscription(String topicId, String subscriberId) {

        subscriptions.values().stream()
                .filter(sub->sub.getTopicId().equals(topicId) && sub.getSubscriberId().equals(subscriberId))
                .findFirst()
                .ifPresent(sub->sub.setActive(false));

    }

    @Override
    public void deleteById(String subscriptionId) {
        subscriptions.remove(subscriptionId);
    }
}
