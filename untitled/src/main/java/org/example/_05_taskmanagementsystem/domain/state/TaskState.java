package org.example._05_taskmanagementsystem.domain.state;

import org.example._05_taskmanagementsystem.domain.Task;
import org.example._05_taskmanagementsystem.domain.TaskStatus;

public interface TaskState {

    boolean canTransitionTo(TaskStatus newStatus);
    void performTransition(Task task,TaskStatus newStatus);
    String getStateName();

}
