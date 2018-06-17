package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.LoggerUtils;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.util.Logger;

public class FactoryUseWaterBehaviour extends WakerBehaviour {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());

    public FactoryUseWaterBehaviour(Agent agent, long timeout) {
        super(agent, timeout);
    }

    public void onStart() {
        String logMessage = "Treatment plant is treating water...";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }

    public void onWake() {
        myAgent.addBehaviour(new FactoryPourWaterBehaviour());
        String logMessage = "Factory tank capacity is full and will pour water";
        LoggerUtils.log(logger, Logger.INFO, logMessage);
    }
}
