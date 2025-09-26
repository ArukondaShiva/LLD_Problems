package org.example._03_trafficsignalsystem.domain;

import org.example._03_trafficsignalsystem.domain.state.RedState;
import org.example._03_trafficsignalsystem.domain.state.TrafficLightState;


public class TrafficLight {

    private Direction direction;
    private TrafficLightState currentState;

    public TrafficLight(Direction direction){
        this.direction = direction;
        this.currentState = new RedState();
        System.out.println("Traffic light created for direction: " + direction + " in RED state");
    }


    public Direction getDirection(){
        return direction;
    }

    public TrafficLightState getCurrentState(){
        return currentState;
    }

    public void setState(TrafficLightState newState){
        this.currentState = currentState;
    }

    public void turnGreen() {
        currentState.turnGreen(this);
    }


    public void turnYellow(){
        currentState.turnYellow(this);
    }

    public void turnRed(){
        currentState.turnRed(this);
    }

    public void turnOff(){
        currentState.turnOff(this);
    }

    public String getCurrentStateName(){
        return currentState.getStateName();
    }

    public boolean canTransitionTo(TrafficLightState newState){
        return currentState.canTransitionTo(newState);
    }

    @Override
    public String toString() {
        return "TrafficLight{" +
                "direction=" + direction +
                ", currentState=" + currentState +
                '}';
    }

}
