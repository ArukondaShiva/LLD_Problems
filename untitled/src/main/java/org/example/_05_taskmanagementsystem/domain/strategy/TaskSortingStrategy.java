package org.example._05_taskmanagementsystem.domain.strategy;

import org.example._05_taskmanagementsystem.domain.Task;

import java.util.List;

public interface TaskSortingStrategy {
    List<Task> sort(List<Task> tasks);
    String getStrategyName();
}
