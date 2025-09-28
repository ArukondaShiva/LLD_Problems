package org.example._05_taskmanagementsystem.domain.observer;

import org.example._05_taskmanagementsystem.domain.ChangeType;

public interface TaskSubscriber {
    void update(int taskId, ChangeType changeType,String oldValue,String newValue);
}
