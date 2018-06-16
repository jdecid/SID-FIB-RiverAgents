package edu.upc.fib.sid.behavours;

import edu.upc.fib.sid.DFUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TreatmentPlantBehaviour extends CyclicBehaviour {
    private AID riverAID;

    public TreatmentPlantBehaviour(Agent agent) {
        super(agent);
        riverAID = DFUtils.getRiverAID(agent);
    }

    public void action() {
        boolean waterTreated = false;
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            ACLMessage reply = msg.createReply();
            switch (msg.getPerformative()) {
                case ACLMessage.INFORM:
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Water received");
                    waterTreated = true;
            }
            if (msg.getPerformative() == ACLMessage.INFORM) {
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Water received");
                waterTreated = true;
            } else {
                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                reply.setContent("Needless to say");
            }
            myAgent.send(reply);

            if (waterTreated) {
                msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(riverAID);
                msg.setContent("Take this clean water");
                myAgent.send(msg);
            }
        } else block();
    }
}
