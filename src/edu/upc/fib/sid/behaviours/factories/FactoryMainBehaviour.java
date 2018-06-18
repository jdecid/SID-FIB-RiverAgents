package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.ReflectionUtils;
import edu.upc.fib.sid.models.WaterTank;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import static edu.upc.fib.sid.helpers.ReflectionUtils.findAndInvokeMethod;

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

        if(!(Boolean) findAndInvokeMethod(myAgent, "getWaitingWaterRequest") &&
                !(Boolean) findAndInvokeMethod(myAgent, "getWaitingWaterPouring")) {

            if(((WaterTank)findAndInvokeMethod(myAgent, "getWasteWaterTank")).isFull()){
                findAndInvokeMethod(myAgent, "setWaitingWaterPouring", true);
                myAgent.addBehaviour(new FactoryPourWaterBehaviour());
            }

            if(((WaterTank)findAndInvokeMethod(myAgent, "getCleanWaterTank")).isFull()) {
                /* Utilitzar */
                int usedWater = 60;
                ((WaterTank)findAndInvokeMethod(myAgent, "getCleanWaterTank")).substractWater(usedWater);
                ((WaterTank)findAndInvokeMethod(myAgent, "getWasteWaterTank")).addWater(usedWater);

            }
            /* Demanar aigua riu */
            else {
                findAndInvokeMethod(myAgent, "setWaitingWaterRequest", true);
                myAgent.addBehaviour(new FactoryRequestWaterBehaviour());
            }

        }
        /*


        boolean isFull = (boolean) ReflectionUtils.findAndInvokeMethod(myAgent, "isTankFull");
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
