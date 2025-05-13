package com.pluralsight;

import java.time.Year;

// Vehicle class inherits from Asset
public class Vehicle extends Asset {
    private String makeModel;
    private int year;
    private int odometer;

    // Constructor
    public Vehicle(String description, String dateAcquired, double originalCost,
                   String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    // Getters and setters
    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    // Overrides getValue with depreciation logic
    @Override
    public double getValue() {
        int currentYear = Year.now().getValue();
        int age = currentYear - year;
        double value;

        if (age <= 3) {
            value = getOriginalCost() * Math.pow(0.97, age); // 3% reduction per year
        } else if (age <= 6) {
            value = getOriginalCost() * Math.pow(0.94, age - 3) * Math.pow(0.97, 3);
        } else if (age <= 10) {
            value = getOriginalCost() * Math.pow(0.92, age - 6) * Math.pow(0.94, 3) * Math.pow(0.97, 3);
        } else {
            value = 1000.0;
        }

        // Reduce value by 25% if over 100,000 miles and not a Honda/Toyota
        if (odometer > 100000 &&
                !(makeModel.toLowerCase().contains("honda") || makeModel.toLowerCase().contains("toyota"))) {
            value *= 0.75;
        }

        return value;
    }
}