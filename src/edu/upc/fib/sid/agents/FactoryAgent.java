package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behaviours.factories.FactoryMainBehaviour;
import edu.upc.fib.sid.models.WaterTank;

public class FactoryAgent extends BaseAgent {
    private WaterTank waterTank;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args.length > 1) {
            name = args[0].toString();
            type = "Factory";
            waterTank = new WaterTank(Integer.parseInt(args[1].toString()));
            addBehaviour(new FactoryMainBehaviour(this, 10000));
            super.setup();
        } else {
            System.err.println("Factories require a name as first parameter, and tank capacity as second one");
            this.doDelete();
        }
    }

    public void stockUpWater(Integer litres) {
        waterTank.addWater(litres);
    }

    public boolean isTankFull() {
        return waterTank.isFull();
    }

    public int emptyTank() {
        return waterTank.empty();
    }
}
