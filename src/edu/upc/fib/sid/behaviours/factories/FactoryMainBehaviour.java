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

        if(!(Boolean) invokeMethod(myAgent, "getWaitingWaterRequest") &&
                !(Boolean) invokeMethod(myAgent, "getWaitingWaterPouring")) {

            if(((WaterTank) invokeMethod(myAgent, "getWasteWaterTank")).isFull()){
                invokeMethod(myAgent, "setWaitingWaterPouring", true);
                myAgent.addBehaviour(new FactoryPourWaterBehaviour());
            }

            if(((WaterTank) invokeMethod(myAgent, "getCleanWaterTank")).isFull()) {
                /* Utilitzar */
                int usedWater = 60;
                ((WaterTank) invokeMethod(myAgent, "getCleanWaterTank")).substractWater(usedWater);
                ((WaterTank) invokeMethod(myAgent, "getWasteWaterTank")).addWater(usedWater);

            }
            /* Demanar aigua riu */
            else {
                invokeMethod(myAgent, "setWaitingWaterRequest", true);
                myAgent.addBehaviour(new FactoryRequestWaterBehaviour());
            }

        }
        /*


        boolean isFull = (boolean) ReflectionUtils.invokeMethod(myAgent, "isTankFull");
        if (isFull) {
            myAgent.addBehaviour(new FactoryPourWaterBehaviour());
        } else {
            myAgent.addBehaviour(new FactoryRequestWaterBehaviour());
        }*/
    }

    private void getAgentsAIDs() {
        Globals.RiverAID = DFUtils.getRiverAID(myAgent);
        Globals.TreatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (Globals.RiverAID != null && Globals.TreatmentPlantAID != null) ready = true;
    }
}
