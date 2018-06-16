package edu.upc.fib.sid.behavours;

import edu.upc.fib.sid.DFUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class FactoryBehaviour extends TickerBehaviour {
    private AID riverAID;

    public FactoryBehaviour(Agent agent, long period) {
        super(agent, period);
        this.riverAID = DFUtils.getRiverAID(myAgent);
        if (this.riverAID == null) {
            System.err.println("Besòs river not found");
            // TODO: Throws unexpected error
            agent.doDelete();
        }
    }

    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(riverAID);
        msg.setContent("Need water");
        myAgent.send(msg);
    }
}
