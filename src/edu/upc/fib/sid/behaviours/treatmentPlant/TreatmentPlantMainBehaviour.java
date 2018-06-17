package edu.upc.fib.sid.behaviours.treatmentPlant;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TreatmentPlantMainBehaviour extends CyclicBehaviour {

    public TreatmentPlantMainBehaviour(Agent agent) {
        super(agent);
    }

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            boolean waterTreated = false;
            switch (msg.getPerformative()) {
                case ACLMessage.INFORM:
                    waterTreated = true; break;
                default:
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    reply.setContent("Needless to say");
                    myAgent.send(reply);
            }

            if (waterTreated) {
                myAgent.addBehaviour(new TreatmentPlantPourWaterBehaviour(myAgent, 2000));
            }
        } else block();
    }
}
