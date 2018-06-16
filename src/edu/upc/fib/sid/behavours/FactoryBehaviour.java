package edu.upc.fib.sid.behavours;

import edu.upc.fib.sid.DFUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class FactoryBehaviour extends TickerBehaviour {
    private AID riverAID;
    private AID treatmentPlantAID;
    private boolean taking = true;
    private boolean ready = false;

    public FactoryBehaviour(Agent agent, long period) {
        super(agent, period);
        getAgentsAIDs();
    }

    protected void onTick() {
        if (!ready) {
            getAgentsAIDs();
            if (!ready) return;
        }

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        if (taking) {
            msg.addReceiver(riverAID);
            msg.setContent("Need water");
        } else {
            msg.addReceiver(treatmentPlantAID);
            msg.setContent("Take this waste water");
        }
        myAgent.send(msg);
        taking = !taking;
    }

    private void getAgentsAIDs() {
        riverAID = DFUtils.getRiverAID(myAgent);
        treatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (riverAID == null) {
            System.err.println("Besòs river not found");
        } else if (treatmentPlantAID == null) {
            System.err.println("Treatment plant not found");
        } else ready = true;
    }
}
