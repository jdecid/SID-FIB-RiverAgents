package edu.upc.fib.sid.behavours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RiverCyclicBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            ACLMessage reply = msg.createReply();
            switch (msg.getPerformative()) {
                case ACLMessage.INFORM:
                    reply.setPerformative(ACLMessage.INFORM);
                    if (msg.getSender().getName().startsWith("Factory")) {
                        reply.setContent("Water sent properly");
                    } else {
                        reply.setContent("Water received properly");
                    }
                    break;
                default:
                    reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    reply.setContent("Needless to say");
            }
            myAgent.send(reply);
        } else block();
    }
}
