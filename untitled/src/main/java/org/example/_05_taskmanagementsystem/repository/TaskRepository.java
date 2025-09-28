package org.example._05_taskmanagementsystem.repository;

import org.example._05_taskmanagementsystem.domain.Task;
import org.example._05_taskmanagementsystem.domain.TaskSearchCriteria;

import java.util.List;

public interface TaskRepository {

    Task save(Task task);
    Task findById(int taskId);
    List<Task> findByAssignee(int assigneeId);
    List<Task> findByParentTask(int parentTaskId);
    List<Task> search(TaskSearchCriteria criteria);
    void delete(int taskId);

}
