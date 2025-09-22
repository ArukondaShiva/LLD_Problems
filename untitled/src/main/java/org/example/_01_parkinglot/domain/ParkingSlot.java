package org.example._01_parkinglot.domain;

import java.util.UUID;

public class ParkingSlot {

    private UUID id;
    private Vehicle.VehicleType slotType;
    private boolean isOccupied;
    private int floorNumber;

    public ParkingSlot(Vehicle.VehicleType slotType,int floorNumber){
        this.id = UUID.randomUUID();
        this.slotType = slotType;
        this.isOccupied = false;
        this.floorNumber = floorNumber;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Vehicle.VehicleType getSlotType() {
        return slotType;
    }

    public void setSlotType(Vehicle.VehicleType slotType) {
        this.slotType = slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotType=" + slotType +
                ", isOccupied=" + isOccupied +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
