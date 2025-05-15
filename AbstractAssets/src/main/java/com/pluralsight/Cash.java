package com.pluralsight;

// New class added to AbstractAssets project
public class Cash extends Asset {

    // Constructor
    public Cash(String description, String dateAcquired, double originalCost) {
        super(description, dateAcquired, originalCost);
    }

    // No fluctuation in value, just return original cost
    @Override
    public double getValue() {
        return getOriginalCost(); // simple return
    }
}