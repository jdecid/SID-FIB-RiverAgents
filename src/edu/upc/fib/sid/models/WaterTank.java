package edu.upc.fib.sid.models;

public class WaterTank {
    private int currentLevel;
    private final int capacity;

    public WaterTank(int capacity) {
        this.capacity = capacity;
        this.currentLevel = 0;
    }

    /**
     * Adds water to tank, all possible quantity until it exceeds the capacity.
     * @return Litres that can't be stocked up in the tank.
     */
    public int addWater(int quantity) {
        currentLevel += quantity;
        int exceeds = currentLevel - capacity;
        if (exceeds > 0) {
            currentLevel = capacity;
            return exceeds;
        } return 0;
    }

    /**
     * Empties tank water, leaving its content at 0.
     * @return Litres emptied.
     */
    public int empty() {
        int litres = currentLevel;
        currentLevel = 0;
        return litres;
    }
}
