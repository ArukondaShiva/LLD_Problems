package org.example._05_taskmanagementsystem.domain.strategy;

import org.example._05_taskmanagementsystem.domain.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrioritySortingStrategy implements TaskSortingStrategy{


    @Override
    public List<Task> sort(List<Task> tasks) {

        List<Task> mutableTasks = new ArrayList<>(tasks);

        mutableTasks.sort(Comparator.comparing(Task::getPriority,
                (p1,p2)->p2.ordinal()-p1.ordinal()));

        return mutableTasks;
    }

    @Override
    public String getStrategyName() {
        return "PRIORITY";
    }

}
