package edu.upc.fib.sid.behaviours.factories;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.upc.fib.sid.helpers.Constants;
import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.ReflectionUtils;
import edu.upc.fib.sid.models.WaterTank;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import static edu.upc.fib.sid.helpers.LoggerUtils.log;
import static edu.upc.fib.sid.helpers.ReflectionUtils.findAndInvokeMethod;

public class FactoryRequestWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private Boolean messageSent = false;

    @Override
    public void action() {
        if (!messageSent) {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(Globals.RiverAID);
            msg.setContent("Need water");
            myAgent.send(msg);
            messageSent = true;
            log(logger, Logger.INFO, "Factory requests water from the river");
        }

        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String senderType = DFUtils.getTypeByAID(myAgent, msg.getSender());
            if (msg.getPerformative() == ACLMessage.CONFIRM && Constants.RIVER.equals(senderType)) {
                findAndInvokeMethod(myAgent, "setWaitingWaterRequest", Boolean.FALSE);
                WaterTank waterTank = (WaterTank) findAndInvokeMethod(myAgent, "getCleanWaterTank");
                waterTank.addWater(60);

                log(logger, Logger.INFO, "Factory receives water from the river");
                return;
            }
        }

        block();
    }
}
