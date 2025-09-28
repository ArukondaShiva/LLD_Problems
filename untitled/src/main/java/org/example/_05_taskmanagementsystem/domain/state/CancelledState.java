package org.example._05_taskmanagementsystem.domain.state;

import org.example._05_taskmanagementsystem.domain.Task;
import org.example._05_taskmanagementsystem.domain.TaskStatus;

public class CancelledState implements TaskState{

    @Override
    public boolean canTransitionTo(TaskStatus newStatus) {
        // Can go to TODO (reactivate)
        return newStatus==TaskStatus.TODO;
    }

    @Override
    public void performTransition(Task task, TaskStatus newStatus) {

        if(canTransitionTo(newStatus)){
            task.setStatus(newStatus);
        }else{
            throw new InvalidStateTransitionException("Cannot transition from CANCELLED to " + newStatus);
        }

    }

    @Override
    public String getStateName() {
        return "CANCELLED";
    }
}
