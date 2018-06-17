package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryMainBehaviour extends TickerBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
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

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(Globals.RiverAID);
        msg.setContent("Need water");
        myAgent.send(msg);

        myAgent.addBehaviour(new FactoryReceiveWaterBehaviour());

        String logMessage = "Factory takes water from the river";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }

    private void getAgentsAIDs() {
        Globals.RiverAID = DFUtils.getRiverAID(myAgent);
        Globals.TreatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (Globals.RiverAID != null && Globals.TreatmentPlantAID != null)
            ready = true;
    }
}
