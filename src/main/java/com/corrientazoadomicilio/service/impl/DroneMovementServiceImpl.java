package com.corrientazoadomicilio.service.impl;

import com.corrientazoadomicilio.model.Command;
import com.corrientazoadomicilio.model.Orientation;
import com.corrientazoadomicilio.model.Position;
import com.corrientazoadomicilio.service.DroneMovementService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DroneMovementServiceImpl implements DroneMovementService {

    private static final Logger logger = Logger.getLogger(DroneMovementServiceImpl.class);

    private Position position = new Position();

    @Override
    public String moveDrone(String directives){
        String[] movements = directives.split("");
        Stream<String> movementsStream = Stream.of(movements);
        movementsStream.forEach(this::move);
        logger.info("Posición de la entrega:");
        logger.info(position.toString());
        return position.toString();
    }

    @Override
    public List<String> moveDrone(List<String> directives) {
        return  directives.stream()
                .map(this::moveDrone)
                .collect(Collectors.toList());
    }

    private void move(String movement){
        logger.info("Posición actual: " + position.toString());
        Command switchValue = Command.valueOf(movement);
        switch (switchValue){
            case A:
                logger.info("Avance");
                switch (position.getOrientation()) {
                    case NORTE:
                        position.increaseX();
                        return;
                    case ORIENTE:
                        position.increaseY();
                        return;
                    case SUR:
                        position.decreaseX();
                        return;
                    case OCCIDENTE:
                        position.decreaseY();
                        return;
                    default:
                        return;
                }
            case I:
                logger.info("Giro a la izquierda");
                switch (position.getOrientation()) {
                    case NORTE:
                        position.setOrientation(Orientation.OCCIDENTE);
                        return;
                    case ORIENTE:
                        position.setOrientation(Orientation.NORTE);
                        return;
                    case SUR:
                        position.setOrientation(Orientation.ORIENTE);
                        return;
                    case OCCIDENTE:
                        position.setOrientation(Orientation.SUR);
                        return;
                    default:
                        return;
                }
            case D:
                logger.info("Giro a la derecha");
                switch (position.getOrientation()) {
                    case NORTE:
                        position.setOrientation(Orientation.ORIENTE);
                        return;
                    case ORIENTE:
                        position.setOrientation(Orientation.SUR);
                        return;
                    case SUR:
                        position.setOrientation(Orientation.OCCIDENTE);
                        return;
                    case OCCIDENTE:
                        position.setOrientation(Orientation.NORTE);
                    default:
                }
        }

    }
}
