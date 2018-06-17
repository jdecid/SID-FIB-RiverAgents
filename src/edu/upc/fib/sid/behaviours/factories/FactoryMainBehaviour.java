package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.ReflectionUtils;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

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

        boolean isFull = (boolean) ReflectionUtils.findAndInvokeMethod(myAgent, "isTankFull");
        if (isFull) {
            myAgent.addBehaviour(new FactoryPourWaterBehaviour());
        } else {
            myAgent.addBehaviour(new FactoryRequestWaterBehaviour());
        }
    }

    private void getAgentsAIDs() {
        Globals.RiverAID = DFUtils.getRiverAID(myAgent);
        Globals.TreatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (Globals.RiverAID != null && Globals.TreatmentPlantAID != null) ready = true;
    }
}
