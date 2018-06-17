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
     * @return True if water quantity exceeds capacity.
     */
    public boolean addWater(int quantity) {
        if (currentLevel + quantity > capacity)
            return true;

        currentLevel += quantity;
        return false;
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
