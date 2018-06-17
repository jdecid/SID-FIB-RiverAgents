package edu.upc.fib.sid.behaviours;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.LoggerUtils;
import edu.upc.fib.sid.helpers.ReflectionUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryBehaviour extends TickerBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private AID riverAID;
    private AID treatmentPlantAID;
    private boolean taking = true;
    private boolean ready = false;
    private int exceeds = 0;

    public FactoryBehaviour(Agent agent, long period) {
        super(agent, period);
    }

    protected void onTick() {
        if (!ready) {
            getAgentsAIDs();
            if (!ready) return;
        }

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        String logMessage;
        if (taking) {
            msg.addReceiver(riverAID);
            msg.setContent("Need water");
            logMessage = "Factory takes water from the river";
        } else {
            taking = true;
            msg.addReceiver(treatmentPlantAID);
            msg.setContent("Take this waste water");
            logMessage = "Factory pours " + exceeds + "L. through the sewage system";
        }
        myAgent.send(msg);

        exceeds = (int) ReflectionUtils.findAndInvokeMethod(myAgent, "stockUpWater", 60);
        if (exceeds > 0) {
            exceeds += (int) ReflectionUtils.findAndInvokeMethod(myAgent, "emptyTank");
            taking = false;
        }

        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }

    private void getAgentsAIDs() {
        riverAID = DFUtils.getRiverAID(myAgent);
        treatmentPlantAID = DFUtils.getTreatmentPlantAID(myAgent);
        if (riverAID == null) {
            System.err.println("Besòs river not found");
        } else if (treatmentPlantAID == null) {
            System.err.println("Treatment plant not found");
        } else ready = true;
    }
}
