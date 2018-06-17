package edu.upc.fib.sid.behaviours;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.behaviours.treatmentPlant.InformReturnTreatedWaterBehaviour;
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
                myAgent.addBehaviour(new InformReturnTreatedWaterBehaviour(myAgent, 2000));
            }
        } else block();
    }
}
