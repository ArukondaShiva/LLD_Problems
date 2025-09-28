package org.example._05_taskmanagementsystem.repository;

import org.example._05_taskmanagementsystem.domain.TaskSubscription;

import java.util.List;

public class TaskSubscriptionRepository {

    public TaskSubscription save(TaskSubscription subscription) {
        // TODO: Implement actual database save
        System.out.println("Saving subscription: User " + subscription.getUserId() + " -> Task " + subscription.getTaskId());
        return subscription;
    }

    public List<TaskSubscription> findByTaskId(int taskId) {
        // TODO: Implement actual database query
        System.out.println("Finding subscriptions for task: " + taskId);
        return new java.util.ArrayList<>();
    }

    public List<TaskSubscription> findByUserId(int userId) {
        // TODO: Implement actual database query
        System.out.println("Finding subscriptions for user: " + userId);
        return new java.util.ArrayList<>();
    }

}
