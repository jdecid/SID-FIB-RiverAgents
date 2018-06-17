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
     * @param quantity of water to stock up.
     */
    public void addWater(int quantity) {
        currentLevel += quantity;
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

    /**
     * Checks if water tank is full.
     * @return True if tank is full.
     */
    public boolean isFull() {
        return currentLevel >= capacity;
    }
}
