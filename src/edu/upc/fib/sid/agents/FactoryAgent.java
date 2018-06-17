package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.models.WaterTank;
import edu.upc.fib.sid.behaviours.FactoryBehaviour;

public class FactoryAgent extends BaseAgent {
    private WaterTank waterTank;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args.length > 1) {
            name = args[0].toString();
            type = "Factory";
            behaviour = new FactoryBehaviour(this, 5000);
            waterTank = new WaterTank(Integer.parseInt(args[1].toString()));
            super.setup();
        } else {
            System.err.println("Factories require a name as first parameter, and tank capacity as second one");
            this.doDelete();
        }
    }

    public int stockUpWater(Integer litres) {
        return waterTank.addWater(litres);
    }

    public int emptyTank() {
        return waterTank.empty();
    }
}
