package edu.upc.fib.sid.behavours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RiverCyclicBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            ACLMessage reply = msg.createReply();
            if (msg.getPerformative() == ACLMessage.REQUEST) {
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Take on me");
            } else if (msg.getPerformative() == ACLMessage.INFORM) {
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Take me on");
            } else {
                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                reply.setContent("Needless to say");
            }
            myAgent.send(reply);
        } else block();
    }
}
