package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryReceiveWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.CONFIRM) {
                myAgent.addBehaviour(new FactoryUseWaterBehaviour(myAgent, 3000));
                String logMessage = "Factory receives water from the river";
                LoggerUtils.log(logger, Logger.INFO, logMessage);
            }
        } else block();
    }
}
