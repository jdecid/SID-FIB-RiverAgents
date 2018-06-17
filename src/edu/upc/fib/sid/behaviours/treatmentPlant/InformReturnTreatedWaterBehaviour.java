package edu.upc.fib.sid.behavours.treatmentPlant;

import edu.upc.fib.sid.DFUtils;
import edu.upc.fib.sid.LoggerUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class InformReturnTreatedWaterBehaviour extends OneShotBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private AID riverAID;

    public InformReturnTreatedWaterBehaviour(Agent agent) {
        super(agent);
        this.riverAID = DFUtils.getRiverAID(agent);
    }

    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(riverAID);
        msg.setContent("Take this clean water");
        myAgent.send(msg);

        String logMessage = "Treatment plant returns water to the river";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }
}
