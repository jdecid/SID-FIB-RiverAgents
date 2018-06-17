package edu.upc.fib.sid.behaviours.treatmentPlant;

import edu.upc.fib.sid.helpers.DFUtils;
import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class TreatmentPlantPourWaterBehaviour extends WakerBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private AID riverAID;

    public TreatmentPlantPourWaterBehaviour(Agent agent, long timeout) {
        super(agent, timeout);
        this.riverAID = DFUtils.getRiverAID(agent);
    }

    public void onStart() {
        String logMessage = "Treatment plant is treating water...";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }

    public void onWake() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(riverAID);
        msg.setContent("Take this clean water");
        myAgent.send(msg);

        String logMessage = "Treatment plant returns water to the river";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }
}
