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

    public FactoryBehaviour(Agent agent, long period) {
        super(agent, period);
        riverAID = DFUtils.getRiverAID(myAgent);
        treatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (riverAID == null) {
            System.err.println("Besòs river not found");
            // TODO: Throws unexpected error
            agent.doDelete();
        } else if (treatmentPlantAID == null) {
            System.err.println("Treatment plant not found");
            // TODO: Throws unexpected error
            agent.doDelete();
        }
    }

    protected void onTick() {
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
}
