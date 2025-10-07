package org.example._08_elevator_system.domain.strategy;

import org.example._08_elevator_system.domain.Elevator;
import org.example._08_elevator_system.domain.ExternalRequest;

import java.util.List;

public class LoadBalancingStrategy implements ElevatorSelectionStrategy{


    @Override
    public Elevator selectElevator(ExternalRequest request, List<Elevator> availableElevators) {

        if(availableElevators.isEmpty()){
            return null;
        }

        Elevator leastLoaded = null;
        int minLoad = Integer.MAX_VALUE;

        for(Elevator elevator : availableElevators){
            if(elevator.isAvailable()){
                continue;
            }

            if(elevator.getCurrentLoad() < minLoad){
                minLoad = elevator.getCurrentLoad();
                leastLoaded = elevator;
            }
        }

        return leastLoaded;
    }
}
