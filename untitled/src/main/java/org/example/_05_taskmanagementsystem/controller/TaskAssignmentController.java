package org.example._05_taskmanagementsystem.controller;

import org.example._05_taskmanagementsystem.service.TaskAssignmentService;

public class TaskAssignmentController {

    private TaskAssignmentService taskAssignmentService;

    public TaskAssignmentController(TaskAssignmentService taskAssignmentService) {
        this.taskAssignmentService = taskAssignmentService;
    }

    public void assignTask(int taskId, int assigneeId) {
        taskAssignmentService.assignTask(taskId, assigneeId);
    }

}
