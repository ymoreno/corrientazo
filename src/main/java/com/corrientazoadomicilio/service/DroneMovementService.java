package com.corrientazoadomicilio.service;

import java.util.List;

public interface DroneMovementService {

    String moveDrone(String directives);

    List<String> moveDrone(List<String> directives);

}
