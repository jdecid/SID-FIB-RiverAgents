package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryPourWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(Globals.TreatmentPlantAID);
        msg.setContent("Take this waste water");
        myAgent.send(msg);

        String logMessage = "Factory throws water through the sewage system";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }
}
