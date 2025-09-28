package org.example._05_taskmanagementsystem.domain.state;

import org.example._05_taskmanagementsystem.domain.Task;
import org.example._05_taskmanagementsystem.domain.TaskStatus;

public class ReviewState implements TaskState{


    @Override
    public boolean canTransitionTo(TaskStatus newStatus) {
        // Can go to COMPLETED, IN_PROGRESS
        return newStatus==TaskStatus.IN_PROGRESS || newStatus==TaskStatus.COMPLETED;
    }

    @Override
    public void performTransition(Task task, TaskStatus newStatus) {
        if(canTransitionTo(newStatus)){
            task.setStatus(newStatus);
        }else{
            throw new InvalidStateTransitionException("Cannot transition from REVIEW to " + newStatus);
        }
    }

    @Override
    public String getStateName() {
        return "REVIEW";
    }

}
