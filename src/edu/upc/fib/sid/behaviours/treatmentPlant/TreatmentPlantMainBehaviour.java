package edu.upc.fib.sid.behaviours.treatmentPlant;

import edu.upc.fib.sid.behaviours.contractNet.RequestPourWaterProposalInitiator;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.models.WaterTank;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;
import org.apache.jena.base.Sys;

import java.util.Date;

import static edu.upc.fib.sid.helpers.LoggerUtils.log;
import static edu.upc.fib.sid.helpers.ReflectionUtils.invokeMethod;

public class TreatmentPlantMainBehaviour extends CyclicBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private Integer pendingValue = 0;

    public TreatmentPlantMainBehaviour(Agent agent) {
        super(agent);
    }

    public void action() {
        Boolean waitingNegotiation = (Boolean) invokeMethod(myAgent, "getWaitingNegotiation");
        Boolean waitingClean = (Boolean) invokeMethod(myAgent, "getWaitingClean");
        if (waitingNegotiation || waitingClean) {
            System.out.println("" + waitingNegotiation + " " + waitingClean);
            return;
        }

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
                if (waterTank.getFullnessPercent() > 50) {
                    if (waterTank.isFull()) {
                        invokeMethod(myAgent, "setWaitingClean", Boolean.TRUE);
                        myAgent.addBehaviour(new TreatmentPlantPourWaterBehaviour(myAgent, 1000));
                    } else {
                        invokeMethod(myAgent, "setWaitingNegotiation", Boolean.TRUE);

                        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                        cfp.setContent("Let's talk about water");
                        for (AID receiver : Globals.FactoriesAIDs) cfp.addReceiver(receiver);
                        cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
                        cfp.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
                        myAgent.addBehaviour(new RequestPourWaterProposalInitiator(myAgent, cfp));
                        log(logger, Logger.INFO, "EDAR starts CFP");
                    }
                }
            }
        } else block();
    }
}
