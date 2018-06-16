package edu.upc.fib.sid.behavours;

import edu.upc.fib.sid.DFUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TreatmentPlantBehaviour extends CyclicBehaviour {
    private AID riverAID;
    private boolean ready;

    public TreatmentPlantBehaviour(Agent agent) {
        super(agent);
        riverAID = DFUtils.getRiverAID(agent);
        ready = riverAID != null;
    }

    public void action() {
        if (!ready) {
            riverAID = DFUtils.getRiverAID(myAgent);
            if (!ready) return;
        }

        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            ACLMessage reply = msg.createReply();
            boolean waterTreated = false;
            switch (msg.getPerformative()) {
                case ACLMessage.REQUEST:
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Water received");
                    waterTreated = true;
                    break;
                default:
                    reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    reply.setContent("Needless to say");
            } myAgent.send(reply);

            if (waterTreated) {
                msg = new ACLMessage(ACLMessage.INFORM);
                System.out.println(riverAID);
                msg.addReceiver(riverAID);
                msg.setContent("Take this clean water");
                myAgent.send(msg);
            }
        } else block();
    }
}
