package com.pluralsight;

// Parent Class
public class Vehicle {
    private String color;
    private int numberOfPassengers;
    private int cargoCapacity;
    private int fuelCapacity;

    // Getters and Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }
//  Part 2
    public static void main(String[] args) {
        Moped slowRide = new Moped();
        slowRide.setColor("Red");
        slowRide.setFuelCapacity(5);
        slowRide.setNumberOfPassengers(1);
        slowRide.setCargoCapacity(10);

        Car sedan = new Car();
        sedan.setColor("Blue");
        sedan.setFuelCapacity(15);
        sedan.setNumberOfPassengers(5);
        sedan.setCargoCapacity(200);

        SemiTruck freightKing = new SemiTruck();
        freightKing.setColor("White");
        freightKing.setFuelCapacity(300);
        freightKing.setNumberOfPassengers(2);
        freightKing.setCargoCapacity(5000);

        Hovercraft hover = new Hovercraft();
        hover.setColor("Silver");
        hover.setFuelCapacity(100);
        hover.setNumberOfPassengers(4);
        hover.setCargoCapacity(150);

        // Output to test
        System.out.println("Moped color: " + slowRide.getColor());
        System.out.println("Car passengers: " + sedan.getNumberOfPassengers());
        System.out.println("Truck cargo: " + freightKing.getCargoCapacity());
        System.out.println("Hovercraft fuel: " + hover.getFuelCapacity());
    }
}