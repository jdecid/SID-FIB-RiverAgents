package edu.upc.fib.sid.behaviours;

import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class RiverBehaviour extends CyclicBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            ACLMessage reply = msg.createReply();
            switch (msg.getPerformative()) {
                case ACLMessage.REQUEST:
                    reply.setPerformative(ACLMessage.CONFIRM);
                    reply.setContent("Take water");

                    String logMessage = "River sends water to Factory";
                    LoggerUtils.log(logger, Logger.INFO, logMessage);
                    break;
                default:
                    reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    reply.setContent("Needless to say");
            }
            myAgent.send(reply);
        } else block();
    }
}
