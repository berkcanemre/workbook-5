package com.pluralsight;

// House class inherits from Asset
public class House extends Asset {
    private String address;
    private int condition; // 1 - excellent, 2 - good, 3 - fair, 4 - poor
    private int squareFoot;
    private int lotSize;

    // Constructor
    public House(String description, String dateAcquired, double originalCost,
                 String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    // Getters and setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    // Overrides getValue to calculate based on condition and lot size
    @Override
    public double getValue() {
        double perSqFt = switch (condition) {
            case 1 -> 180.0;
            case 2 -> 130.0;
            case 3 -> 90.0;
            case 4 -> 80.0;
            default -> 0.0;
        };

        double houseValue = perSqFt * squareFoot;
        double lotValue = 0.25 * lotSize;

        return houseValue + lotValue;
    }
}