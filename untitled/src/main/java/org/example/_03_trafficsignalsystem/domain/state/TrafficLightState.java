package org.example._03_trafficsignalsystem.domain.state;

import org.example._03_trafficsignalsystem.domain.TrafficLight;

public interface TrafficLightState {

    void turnGreen(TrafficLight trafficLight);
    void turnYellow(TrafficLight trafficLight);
    void turnRed(TrafficLight trafficLight);
    void turnOff(TrafficLight trafficLight);
    String getStateName();
    boolean canTransitionTo(TrafficLightState newState);

}
