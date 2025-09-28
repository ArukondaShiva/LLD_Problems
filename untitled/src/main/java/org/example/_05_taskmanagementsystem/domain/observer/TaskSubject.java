package org.example._05_taskmanagementsystem.domain.observer;

import org.example._05_taskmanagementsystem.domain.ChangeType;

public interface TaskSubject {
    void attach(TaskSubscriber subscriber);
    void detach(TaskSubscriber subscriber);
    void notifySubscribers(ChangeType changeType,String oldValue,String newValue);
}
