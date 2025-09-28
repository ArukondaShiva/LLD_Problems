package org.example._05_taskmanagementsystem.domain.state;

import org.example._05_taskmanagementsystem.domain.Task;
import org.example._05_taskmanagementsystem.domain.TaskStatus;

public class InProgressState implements TaskState{


    @Override
    public boolean canTransitionTo(TaskStatus newStatus) {
        // Can go to REVIEW, CANCELLED
        return newStatus ==TaskStatus.REVIEW || newStatus==TaskStatus.CANCELLED;
    }

    @Override
    public void performTransition(Task task, TaskStatus newStatus) {
        if(canTransitionTo(newStatus)){
            task.setStatus(newStatus);
        }else{
            throw new InvalidStateTransitionException("Cannot transition from IN_PROGRESS to " + newStatus);
        }
    }

    @Override
    public String getStateName() {
        return "IN_PROGRESS";
    }
}
