package com.pluralsight;

// Parent Class
public class Vehicle {
    private String color;
    private int numberOfPassengers;
    private int cargoCapacity;
    private int fuelCapacity;

    // Constructor - Updated
    public Vehicle(String color, int numberOfPassengers, int cargoCapacity, int fuelCapacity) {
        this.color = color;
        this.numberOfPassengers = numberOfPassengers;
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
    }

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
    //Part 3
    public class Vehicles {
        public static void main(String[] args) {
            // Creating objects using constructors
            Moped slowRide = new Moped("Red", 1, 10, 5);
            Car sedan = new Car("Blue", 5, 200, 15);
            SemiTruck freightKing = new SemiTruck("White", 2, 5000, 300);
            Hovercraft hover = new Hovercraft("Silver", 4, 150, 100);

            // Display vehicle information
            System.out.println("Moped color: " + slowRide.getColor());
            System.out.println("Car passengers: " + sedan.getNumberOfPassengers());
            System.out.println("Truck cargo capacity: " + freightKing.getCargoCapacity());
            System.out.println("Hovercraft fuel capacity: " + hover.getFuelCapacity());
        }
    }
}