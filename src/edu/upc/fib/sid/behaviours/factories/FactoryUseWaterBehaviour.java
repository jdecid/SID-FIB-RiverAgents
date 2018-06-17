package edu.upc.fib.sid.behaviours.factories;

import edu.upc.fib.sid.helpers.LoggerUtils;
import edu.upc.fib.sid.helpers.ReflectionUtils;
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
        String logMessage;
        boolean isFull = (boolean)
                ReflectionUtils.findAndInvokeMethod(myAgent, "stockUpWater", 60);
        if (!isFull) {
            myAgent.addBehaviour(new FactoryPourWaterBehaviour());
            logMessage = "Factory tank capacity is full and will pour water";
        } else {
            logMessage = "Factory keeps storing water";
        } LoggerUtils.log(logger, Logger.INFO, logMessage);
    }
}
