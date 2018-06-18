package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.models.WaterTank;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import static edu.upc.fib.sid.helpers.ReflectionUtils.invokeMethod;

public class FactoryMainBehaviour extends TickerBehaviour {
    private boolean ready = false;

    public FactoryMainBehaviour(Agent agent, long period) {
        super(agent, period);
    }

    @Override
    protected void onTick() {
        if (!ready) {
            getAgentsAIDs();
            if (!ready) return;
        }

        Boolean waitingWaterRequest = (Boolean) invokeMethod(myAgent, "getWaitingWaterRequest");
        Boolean waitingWaterPouring = (Boolean) invokeMethod(myAgent, "getWaitingWaterPouring");

        if (!waitingWaterRequest && !waitingWaterPouring) {
            WaterTank wasteWaterTank = (WaterTank) invokeMethod(myAgent, "getWasteWaterTank");
            if (wasteWaterTank.isFull()) {
                invokeMethod(myAgent, "setWaitingWaterPouring", Boolean.TRUE);
                myAgent.addBehaviour(new FactoryPourWaterBehaviour());
                return;
            }

            WaterTank cleanWaterTank = (WaterTank) invokeMethod(myAgent, "getCleanWaterTank");
            if (!cleanWaterTank.isEmpty()) {
                int usedWater = 50;
                cleanWaterTank.subtractWater(usedWater);
                wasteWaterTank.addWater(usedWater);
                return;
            }

            invokeMethod(myAgent, "setWaitingWaterRequest", Boolean.TRUE);
            myAgent.addBehaviour(new FactoryRequestWaterBehaviour());
        }
    }

    private void getAgentsAIDs() {
        Globals.RiverAID = DFUtils.getRiverAID(myAgent);
        Globals.TreatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (Globals.RiverAID != null && Globals.TreatmentPlantAID != null) ready = true;
    }
}
