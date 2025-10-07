package org.example._08_elevator_system.domain.strategy;

import org.example._08_elevator_system.domain.Elevator;
import org.example._08_elevator_system.domain.ExternalRequest;

import java.util.List;

public interface ElevatorSelectionStrategy {

    Elevator selectElevator(ExternalRequest request, List<Elevator> availableElevators);

}
