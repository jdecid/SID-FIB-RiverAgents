package edu.upc.fib.sid.behaviours.treatmentPlant;

import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class TreatmentPlantMainBehaviour extends CyclicBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    public TreatmentPlantMainBehaviour(Agent agent) {
        super(agent);
    }

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.QUERY_IF) {
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.CONFIRM);
                reply.setContent("OK, pour water");
                myAgent.send(reply);

                String logMessage = "Treatment plant allows Factory to pour water";
                LoggerUtils.log(logger, Logger.INFO, logMessage);
            } else if (msg.getPerformative() == ACLMessage.INFORM) {
                myAgent.addBehaviour(new TreatmentPlantPourWaterBehaviour(myAgent, 1000));
            }
        } else block();
    }
}
