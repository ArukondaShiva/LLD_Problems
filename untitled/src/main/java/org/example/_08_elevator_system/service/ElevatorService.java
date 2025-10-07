package org.example._08_elevator_system.service;

import org.example._08_elevator_system.domain.Elevator;
import org.example._08_elevator_system.domain.ElevatorState;
import org.example._08_elevator_system.domain.ExternalRequest;
import org.example._08_elevator_system.domain.InternalRequest;
import org.example._08_elevator_system.domain.state.PreMaintenanceState;
import org.example._08_elevator_system.repository.ElevatorRepository;
import org.example._08_elevator_system.repository.impl.ElevatorRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ElevatorService {

    private final ElevatorRepository elevatorRepository;
    private RequestService requestService;

    public ElevatorService(){
        this.elevatorRepository = new ElevatorRepositoryImpl();
    }


    // Setter for dependency injection
    public void setRequestService(RequestService requestService){
        this.requestService=requestService;
    }


    public Elevator createElevator(String buildingId,int capacity){
        Elevator elevator = new Elevator(buildingId, capacity);
        return elevatorRepository.save(elevator);
    }

    public void updateElevatorState(String elevatorId, ElevatorState state){
        elevatorRepository.findById(elevatorId).ifPresent(elevator -> {
            elevator.setState(state);
            elevatorRepository.save(elevator);
        });
    }

    public void updateElevatorFloor(String elevatorId,int floor){
        elevatorRepository.findById(elevatorId).ifPresent(elevator -> {
            elevator.setCurrentFloor(floor);
            elevatorRepository.save(elevator);
        });
    }

    public List<Elevator> getAvailableElevators(String buildingId){
        return elevatorRepository.findAvailableElevators(buildingId);
    }

    public List<Elevator> getAllElevators(String buildingId){
        return elevatorRepository.findByBuilding(buildingId);
    }

    public Elevator findById(String elevatorId) {
        return elevatorRepository.findById(elevatorId).orElse(null);
    }


    public void setMaintenanceMode(String elevatorId,boolean maintenance){

        elevatorRepository.findById(elevatorId).ifPresent(elevator -> {

            if(maintenance){
                // Always use graceful degradation - go to pre-maintenance first
                if(hasPendingRequests(elevatorId) || hasAssignedRequests(elevatorId)){
                    elevator.setStateHandler(new PreMaintenanceState());
                    System.out.println("Elevator " + elevatorId + " entering pre-maintenance mode to complete pending requests gracefully");
                }else{
                    elevator.enterMaintenance();// Direct to maintenance if no requests at all
                    System.out.println("Elevator " + elevatorId + " entering maintenance mode directly (no pending requests)");
                }
            }else{
                elevator.exitMaintenance();
            }

            elevatorRepository.save(elevator);
        });

    }


    private boolean hasPendingRequests(String elevatorId){
        if(requestService==null){
            System.out.println("Warning: RequestService not injected, cannot check pending requests");
            return false;
        }

        List<InternalRequest> pendingRequests = requestService.getPendingRequestsForElevator(elevatorId);
        return !pendingRequests.isEmpty();
    }


    private boolean hasAssignedRequests(String elevatorId){
        if(requestService==null){
            System.out.println("Warning: RequestService not injected, cannot check assigned requests");
            return false;
        }

        List<ExternalRequest> assignedRequests = requestService.getAssignedRequestsForElevator(elevatorId);
        return !assignedRequests.isEmpty();
    }

    public List<Elevator> getElevatorsByBuilding(String buildingId){
        return elevatorRepository.findByBuilding(buildingId);
    }

    public void setPreMaintenanceMode(String elevatorId){
        elevatorRepository.findById(elevatorId).ifPresent(elevator -> {
            elevator.setStateHandler(new PreMaintenanceState());
            elevatorRepository.save(elevator);
            System.out.println("Elevator " + elevatorId + " set to pre-maintenance mode");
        });
    }


    public void checkMaintenanceTransition(String elevatorId){
        elevatorRepository.findById(elevatorId).ifPresent(elevator -> {
            if(elevator.getStateHandler() instanceof PreMaintenanceState){
                if(!hasPendingRequests(elevatorId)){
                    elevator.enterMaintenance();
                    elevatorRepository.save(elevator);
                    System.out.println("Elevator " + elevatorId + " transitioned to maintenance mode");
                }
            }
        });
    }



}
