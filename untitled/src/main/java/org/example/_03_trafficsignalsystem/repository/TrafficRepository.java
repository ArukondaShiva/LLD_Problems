package org.example._03_trafficsignalsystem.repository;

import org.example._03_trafficsignalsystem.domain.Direction;
import org.example._03_trafficsignalsystem.domain.VehicleCounter;

import java.util.HashMap;
import java.util.Map;

public class TrafficRepository {

    private Map<Direction, VehicleCounter> vehicleCounters;


    public TrafficRepository(){
        this.vehicleCounters = new HashMap<>();
        // Initialize counters for all directions
        for(Direction direction : Direction.values()){
            this.vehicleCounters.put(direction,new VehicleCounter(direction));
        }
    }


    public void updateCount(Direction direction, int count) {
        VehicleCounter counter = vehicleCounters.get(direction);
        if (counter != null) {
            counter.setCount(count);
            System.out.println("Vehicle count updated for " + direction + ": " + count);
        } else {
            System.out.println("Vehicle counter not found for direction: " + direction);
        }
    }



    public int getCount(Direction direction) {
        VehicleCounter counter = vehicleCounters.get(direction);
        if (counter != null) {
            System.out.println("Vehicle count retrieved for " + direction + ": " + counter.getCount());
            return counter.getCount();
        } else {
            System.out.println("Vehicle counter not found for direction: " + direction);
            return 0;
        }
    }



    public void incrementCount(Direction direction) {
        VehicleCounter counter = vehicleCounters.get(direction);
        if (counter != null) {
            counter.incrementCount();
        } else {
            System.out.println("Vehicle counter not found for direction: " + direction);
        }
    }



    public void resetCount(Direction direction) {
        VehicleCounter counter = vehicleCounters.get(direction);
        if (counter != null) {
            counter.resetCount();
        } else {
            System.out.println("Vehicle counter not found for direction: " + direction);
        }
    }


    public VehicleCounter getCounter(Direction direction) {
        return vehicleCounters.get(direction);
    }

}
