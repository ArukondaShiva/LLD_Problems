package org.example._06_pubsubsystem.domain.observer;

import org.example._06_pubsubsystem.domain.Message;

public interface SubscriberObserver {

    void update(Message message);

}
