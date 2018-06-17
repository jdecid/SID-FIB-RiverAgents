package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.LoggerUtils;
import edu.upc.fib.sid.helpers.ReflectionUtils;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryPourWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    @Override
    public void action() {
        try {
            int litres = (int) ReflectionUtils.findAndInvokeMethod(myAgent, "emptyTank");
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(DFUtils.getTreatmentPlantAID(myAgent));
            msg.setContent("Take this waste water");
            myAgent.send(msg);

            String logMessage = "Factory throws " + litres + "L. through the sewage system";
            LoggerUtils.log(logger, Logger.INFO, logMessage);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
