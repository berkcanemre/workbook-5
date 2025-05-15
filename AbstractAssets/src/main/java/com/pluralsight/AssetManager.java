package com.pluralsight;

import java.util.ArrayList;

public class AssetManager {
    public static void main(String[] args) {
        // Create asset list
        ArrayList<Asset> myAssets = new ArrayList<>();

        // Add houses
        myAssets.add(new House("My main house", "2020-05-12", 250000.00,
                "123 Oak Lane", 1, 2000, 5000));
        myAssets.add(new House("My vacation home", "2018-07-01", 180000.00,
                "456 Lakeview Drive", 2, 1500, 6000));

        // Add vehicles
        myAssets.add(new Vehicle("Tom's truck", "2022-03-15", 35000.00,
                "Ford F-150", 2021, 40000));
        myAssets.add(new Vehicle("My car", "2015-10-10", 22000.00,
                "Toyota Camry", 2015, 120000));

        // Display all assets
        for (Asset asset : myAssets) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Description: " + asset.getDescription());
            System.out.println("Date Acquired: " + asset.getDateAcquired());
            System.out.printf("Original Cost: $%.2f\n", asset.getOriginalCost());
            System.out.printf("Current Value: $%.2f\n", asset.getValue());

            // Determine if it's a House or Vehicle and display additional info
            if (asset instanceof House) {
                House house = (House) asset;
                System.out.println("Type: House at " + house.getAddress());
            } else if (asset instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) asset;
                System.out.println("Type: Vehicle - " + vehicle.getYear() + " " + vehicle.getMakeModel());
            } else if (asset instanceof Cash) {
                System.out.println("Type: Cash (stored securely)");
            }
        }
    }
}