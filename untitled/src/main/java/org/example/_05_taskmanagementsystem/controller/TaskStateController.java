package org.example._05_taskmanagementsystem.controller;

import org.example._05_taskmanagementsystem.domain.TaskStatus;
import org.example._05_taskmanagementsystem.service.TaskStateService;

public class TaskStateController {

    private TaskStateService taskStateService;

    public TaskStateController(TaskStateService taskStateService) {
        this.taskStateService = taskStateService;
    }

    public void updateTaskStatus(int taskId, TaskStatus newStatus) {
        taskStateService.updateTaskStatus(taskId, newStatus);
    }

}
