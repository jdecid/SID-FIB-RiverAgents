package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.*;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryPourWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.addReceiver(Globals.TreatmentPlantAID);
        msg.setContent("Can I pour water?");
        myAgent.send(msg);

        LoggerUtils.log(logger, Logger.INFO, "Factory queries to pour water through the sewage system");

        msg = myAgent.receive();
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.CONFIRM) {
                String senderType = DFUtils.getTypeByAID(myAgent, msg.getSender());
                if (Constants.TREATMENT_PLANT.equals(senderType)) {
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("I pour waste water");
                    myAgent.send(reply);

                    ReflectionUtils.findAndInvokeMethod(myAgent, "emptyTank");
                    LoggerUtils.log(logger, Logger.INFO, "Factory pours water through the sewage system");
                }
            }
        } else block();
    }
}
