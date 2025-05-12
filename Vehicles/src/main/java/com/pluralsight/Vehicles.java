package com.pluralsight;
//  Part 2 moved here combines with Part 3
public class Vehicles {
    public static void main(String[] args) {
        // Creating objects using parameterized constructors
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