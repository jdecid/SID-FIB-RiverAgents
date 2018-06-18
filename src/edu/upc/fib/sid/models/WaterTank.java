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
     * Subtracts water from tank
     * @param quantity of water to subtract
     */
    public void subtractWater(int quantity) {
        currentLevel -= quantity;
        if (currentLevel < 0) currentLevel = 0;
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

    /**
     * Checks if water tank is empty.
     * @return True if tank is empty.
     */
    public boolean isEmpty() {
        return currentLevel == 0;
    }
}
