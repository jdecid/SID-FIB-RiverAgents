package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class FactoryMainBehaviour extends TickerBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private AID riverAID;
    private boolean ready = false;

    public FactoryMainBehaviour(Agent agent, long period) {
        super(agent, period);
    }

    @Override
    protected void onTick() {
        if (!ready) {
            getAgentsAIDs();
            if (!ready) return;
        }

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(riverAID);
        msg.setContent("Need water");
        myAgent.addBehaviour(new FactoryReceiveWaterBehaviour());
        myAgent.send(msg);

        String logMessage = "Factory takes water from the river";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }

    private void getAgentsAIDs() {
        riverAID = DFUtils.getRiverAID(myAgent);
        if (riverAID == null) System.out.println("Besòs river not found");
        else ready = true;
    }
}
