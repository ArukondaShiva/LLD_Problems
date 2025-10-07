package org.example._08_elevator_system.domain.state;

import org.example._08_elevator_system.domain.Elevator;

public interface ElevatorStateHandler {


    //Handle door opening based on current state
    void openDoors(Elevator elevator);


    //Handle door closing based on current state
    void closeDoors(Elevator elevator);


    //Handle maintenance mode transition
    void enterMaintenance(Elevator elevator);


    //Handle exit from maintenance mode
    void exitMaintenance(Elevator elevator);


    //Check if elevator can accept new external requests
    boolean canAcceptExternalRequests(Elevator elevator);


    //Check if elevator can accept new internal requests
    boolean canAcceptInternalRequests(Elevator elevator);


    //Get the state name for logging/debugging
    String getStateName();


}
