package edu.upc.fib.sid.behaviours.treatmentPlant;

import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class TreatmentPlantPourWaterBehaviour extends WakerBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    public TreatmentPlantPourWaterBehaviour(Agent agent, long timeout) {
        super(agent, timeout);
    }

    public void onStart() {
        String logMessage = "Treatment plant is treating water...";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }

    public void onWake() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(Globals.RiverAID);
        msg.setContent("Take this clean water");
        myAgent.send(msg);

        String logMessage = "Treatment plant returns water to the river";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }
}
