package org.example._08_elevator_system.service;

import org.example._08_elevator_system.domain.Elevator;
import org.example._08_elevator_system.domain.ExternalRequest;
import org.example._08_elevator_system.domain.strategy.ElevatorSelectionStrategy;
import org.example._08_elevator_system.domain.strategy.NearestElevatorStrategy;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DispatcherService {

    private final RequestService requestService;
    private final ElevatorService elevatorService;
    private ElevatorSelectionStrategy selectionStrategy;
    private final BlockingDeque<ExternalRequest> requestQueue;

    public DispatcherService(RequestService requestService,ElevatorService elevatorService){
        this.requestService = requestService;
        this.elevatorService = elevatorService;
        this.selectionStrategy = new NearestElevatorStrategy();
        this.requestQueue = new LinkedBlockingDeque<>();
    }

    public void queueExternalRequest(ExternalRequest request){
        try {
            requestQueue.put(request);
            System.out.println("Request queued: Floor " + request.getFloorNumber() + " " + request.getDirection());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Elevator selectBestElevator(ExternalRequest request, List<Elevator> availableElevators){
        return selectionStrategy.selectElevator(request,availableElevators);
    }

    public void assignRequestToElevator(ExternalRequest request,Elevator elevator){
        requestService.assignRequestToElevator(request.getId(),elevator.getId());
        System.out.println("Request assigned: Floor " + request.getFloorNumber() + " -> Elevator " + elevator.getId());
    }

    public void processPendingRequests(String buildingId){

        while(!requestQueue.isEmpty()){

            try{

                ExternalRequest request = requestQueue.take();
                if(!request.getBuildingId().equals(buildingId)){
                    requestQueue.put(request); // Put back if different building
                    break;
                }

                // not in pre-maintenance or maintenance mode
                List<Elevator> availableElevators = elevatorService.getAvailableElevators(buildingId);
                Elevator selectedElevator = selectBestElevator(request, availableElevators);

                if (selectedElevator != null) {
                    assignRequestToElevator(request, selectedElevator);
                } else {
                    // No available elevator, put back in queue
                    requestQueue.put(request);
                    break;
                }

            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }

        }

    }


    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy strategy) {
        this.selectionStrategy = strategy;
    }

    public int getQueueSize() {
        return requestQueue.size();
    }


    public void processExternalRequest(ExternalRequest request, String buildingId) {
        List<Elevator> availableElevators = elevatorService.getAvailableElevators(buildingId);
        Elevator selectedElevator = selectBestElevator(request, availableElevators);

        if (selectedElevator != null) {
            assignRequestToElevator(request, selectedElevator);
        } else {
            queueExternalRequest(request);
        }
    }


}
