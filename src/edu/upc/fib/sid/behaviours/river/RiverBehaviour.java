package edu.upc.fib.sid.behaviours.river;

import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class RiverBehaviour extends CyclicBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String logMessage = "";
            switch (msg.getPerformative()) {
                case ACLMessage.REQUEST:
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.CONFIRM);
                    reply.setContent("Take water");
                    myAgent.send(reply);

                    logMessage = "River sends water to Factory";
                    break;
                case ACLMessage.INFORM:
                    logMessage = "River gets clean water";
            }
            LoggerUtils.log(logger, Logger.INFO, logMessage);
        } else block();
    }
}
