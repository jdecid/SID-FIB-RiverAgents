package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.*;
import edu.upc.fib.sid.models.WaterTank;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import static edu.upc.fib.sid.helpers.LoggerUtils.log;
import static edu.upc.fib.sid.helpers.ReflectionUtils.findAndInvokeMethod;

public class FactoryPourWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private Boolean messageSent = false;

    @Override
    public void action() {
        if (!messageSent) {
            ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
            msg.addReceiver(Globals.TreatmentPlantAID);
            msg.setContent("Can I pour water?");
            myAgent.send(msg);
            messageSent = true;
            log(logger, Logger.INFO, "Factory queries to pour water through the sewage system");
        }

        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String senderType = DFUtils.getTypeByAID(myAgent, msg.getSender());
            if (Constants.TREATMENT_PLANT.equals(senderType)) {
                if (msg.getPerformative() == ACLMessage.CONFIRM) {
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("I pour waste water");
                    myAgent.send(reply);

                    findAndInvokeMethod(myAgent, "setWaitingWaterRequest", Boolean.FALSE);
                    WaterTank waterTank = (WaterTank) findAndInvokeMethod(myAgent, "getWasteWaterTank");
                    waterTank.empty();
                    
                    log(logger, Logger.INFO, "Factory pours water through the sewage system");
                    return;
                } else if (msg.getPerformative() == ACLMessage.DISCONFIRM) {
                    log(logger, Logger.INFO, "Factory can't pour water through the sewage system");
                    messageSent = false;
                }
            }
        }

        block();
    }
}
