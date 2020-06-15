package com.corrientazoadomicilio.model;

public class Position {

    private int x;
    private int y;
    private Orientation orientation;

    public Position() {
        x=0;
        y=0;
        orientation=Orientation.NORTE;
    }

    public void increaseX(){
        this.x++;
    }

    public void increaseY(){
        this.y++;
    }

    public void decreaseX(){
        this.x--;
    }

    public void decreaseY(){
        this.y--;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "("+y+","+x+") dirección "+ orientation.toString().substring(0,1) +orientation.toString().substring(1).toLowerCase();
    }
}
