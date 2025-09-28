package org.example._05_taskmanagementsystem.domain.strategy;

import org.example._05_taskmanagementsystem.domain.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DueDateSortingStrategy implements TaskSortingStrategy{


    @Override
    public List<Task> sort(List<Task> tasks) {

        List<Task> mutableTasks = new ArrayList<>(tasks);

        mutableTasks.sort(Comparator.comparing(Task::getDueDate,
                Comparator.nullsLast(Comparator.naturalOrder())));

        return mutableTasks;
    }

    @Override
    public String getStrategyName() {
        return "DUE_DATE";
    }

}
