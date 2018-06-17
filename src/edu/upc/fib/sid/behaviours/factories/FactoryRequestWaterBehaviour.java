package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.Constants;
import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryRequestWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(Globals.RiverAID);
        msg.setContent("Need water");
        myAgent.send(msg);
        LoggerUtils.log(logger, Logger.INFO, "Factory requests water from the river");

        msg = myAgent.receive();
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.CONFIRM) {
                String senderType = DFUtils.getTypeByAID(myAgent, msg.getSender());
                if (Constants.RIVER.equals(senderType)) {
                    myAgent.addBehaviour(new FactoryUseWaterBehaviour(myAgent, 3000));
                    LoggerUtils.log(logger, Logger.INFO, "Factory receives water from the river");
                }
            }
        } else block();
    }
}
