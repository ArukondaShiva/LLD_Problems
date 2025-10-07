package org.example._08_elevator_system.domain.state;

import org.example._08_elevator_system.domain.Elevator;
import org.example._08_elevator_system.domain.ElevatorState;

public class DoorsOpeningState implements ElevatorStateHandler{


    @Override
    public void openDoors(Elevator elevator) {
        // Already opening
        System.out.println("Elevator " + elevator.getId() + " doors already opening");
    }

    @Override
    public void closeDoors(Elevator elevator) {
        elevator.setState(ElevatorState.DOORS_CLOSING);
        elevator.setStateHandler(new DoorsClosingState());
        System.out.println("Elevator " + elevator.getId() + " closing doors");
    }

    @Override
    public void enterMaintenance(Elevator elevator) {
        // Close doors first, then enter maintenance
        closeDoors(elevator);
        elevator.getStateHandler().enterMaintenance(elevator);
    }

    @Override
    public void exitMaintenance(Elevator elevator) {
        // Not in maintenance, no action needed
        System.out.println("Elevator " + elevator.getId() + " is not in maintenance mode");
    }

    @Override
    public boolean canAcceptExternalRequests(Elevator elevator) {
        return elevator.isActive() && !elevator.isFull();
    }

    @Override
    public boolean canAcceptInternalRequests(Elevator elevator) {
        return elevator.isActive();
    }

    @Override
    public String getStateName() {
        return "DOORS_OPENING";
    }
}
