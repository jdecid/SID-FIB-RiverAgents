package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behaviours.factories.FactoryMainBehaviour;
import edu.upc.fib.sid.models.WaterTank;

public class FactoryAgent extends BaseAgent {
    private WaterTank cleanWaterTank;
    private WaterTank wasteWaterTank;
    private Boolean waitingWaterRequest = false;
    private Boolean waitingWaterPouring = false;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args.length > 1) {
            name = args[0].toString();
            type = "Factory";
            cleanWaterTank = new WaterTank(Integer.parseInt(args[1].toString()));
            wasteWaterTank = new WaterTank(Integer.parseInt(args[1].toString()));
            addBehaviour(new FactoryMainBehaviour(this, 2000));
            super.setup();
        } else {
            System.err.println("Factories require a name as first parameter, and tank capacity as second one");
            this.doDelete();
        }
    }

    public WaterTank getCleanWaterTank() {
        return cleanWaterTank;
    }

    public WaterTank getWasteWaterTank() {
        return wasteWaterTank;
    }

    public Boolean getWaitingWaterRequest() {
        return waitingWaterRequest;
    }

    public void setWaitingWaterRequest(Boolean waitingWaterRequest) {
        this.waitingWaterRequest = waitingWaterRequest;
    }

    public Boolean getWaitingWaterPouring() {
        return waitingWaterPouring;
    }

    public void setWaitingWaterPouring(Boolean waitingWaterPouring) {
        this.waitingWaterPouring = waitingWaterPouring;
    }
}
