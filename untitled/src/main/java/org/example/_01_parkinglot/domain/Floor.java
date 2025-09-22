package org.example._01_parkinglot.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Floor {

    private UUID id;
    private int floorNumber;
    private List<ParkingSlot> slots;


    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }



    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void addSlot(ParkingSlot slot) {
        this.slots.add(slot);
    }


    public List<ParkingSlot> getAvailableSlots(Vehicle.VehicleType vehicleType){
        List<ParkingSlot> availableSlots = new ArrayList<>();
        for(ParkingSlot slot : slots){
            if(slot.getSlotType()==vehicleType && !slot.isOccupied()){
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }


    public int getAvailableSlotsCount(Vehicle.VehicleType vehicleType) {
        return getAvailableSlots(vehicleType).size();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setSlots(List<ParkingSlot> slots) {
        this.slots = slots;
    }


    public int getTotalSlots() {
        return slots.size();
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", slots=" + slots +
                '}';
    }
}
