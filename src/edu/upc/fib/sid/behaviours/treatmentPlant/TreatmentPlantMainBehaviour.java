package edu.upc.fib.sid.behaviours.treatmentPlant;

import edu.upc.fib.sid.models.WaterTank;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import static edu.upc.fib.sid.helpers.LoggerUtils.log;
import static edu.upc.fib.sid.helpers.ReflectionUtils.invokeMethod;

public class TreatmentPlantMainBehaviour extends CyclicBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private Integer pendingValue = 0;

    public TreatmentPlantMainBehaviour(Agent agent) {
        super(agent);
    }

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            WaterTank waterTank = (WaterTank) invokeMethod(myAgent, "getWasteWaterTank");
            if (msg.getPerformative() == ACLMessage.QUERY_IF) {
                ACLMessage reply = msg.createReply();
                if (waterTank.hasEnoughCapacity(pendingValue + 50)) {
                    reply.setPerformative(ACLMessage.CONFIRM);
                    reply.setContent("OK, pour water");
                    pendingValue += 50;
                    log(logger, Logger.INFO, "EDAR allows Factory to pour water");
                } else {
                    reply.setPerformative(ACLMessage.DISCONFIRM);
                    reply.setContent("Nope, don't pour water");
                    log(logger, Logger.INFO, "EDAR disallows Factory to pour water");
                }
                myAgent.send(reply);
            } else if (msg.getPerformative() == ACLMessage.INFORM) {
                waterTank.addWater(50);
                pendingValue -= 50;
                log(logger, Logger.INFO, "Factory pours 50L to EDAR");
                if (waterTank.isFull()) {
                    myAgent.addBehaviour(new TreatmentPlantPourWaterBehaviour(myAgent, 1000));
                }
            }
        } else block();
    }
}
