package org.example._08_elevator_system.domain.strategy;

import org.example._08_elevator_system.domain.Elevator;
import org.example._08_elevator_system.domain.InternalRequest;

import java.util.List;

public interface MovementStrategy {

    List<Integer> calculatePath(Elevator elevator, List<InternalRequest> requests);

}
