package com.corrientazoadomicilio.service;

import com.corrientazoadomicilio.service.impl.DroneMovementServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DroneMovementServiceTest {

    private DroneMovementService droneMovementService = new DroneMovementServiceImpl();

    @Test
    public void reportDeliveryTest(){
        String delivery = droneMovementService.moveDrone("AAAAIAA");
        //Hay un solo giro a la izquierda lo que cambia la dirección a Occidente
        assertNotEquals("(-2,4) dirección Norte", delivery);
        assertEquals("(-2,4) dirección Occidente", delivery);
        //Al cambiar el inicio de la segunda entrega modifica totalmente lo esperado
        String secondDelivery = droneMovementService.moveDrone("DDDAIAD");
        assertNotEquals("(-3,3) dirección Sur", secondDelivery);
        assertEquals("(-1,3) dirección Sur", secondDelivery);
        //Al igual que la tercera entrega
        String thirdDelivery = droneMovementService.moveDrone("AAAAIAA");
        assertNotEquals("(-4,2) dirección Oriente", thirdDelivery);
        assertEquals("(1,-1) dirección Oriente", thirdDelivery);
    }

    @Test
    public void circularTourBackToTop(){
        //Test básico para probar un giro de 360 grados
        String delivery = droneMovementService.moveDrone("ADADADAD");
        assertEquals("(0,0) dirección Norte", delivery);
    }

}