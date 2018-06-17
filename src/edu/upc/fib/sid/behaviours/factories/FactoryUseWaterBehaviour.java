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
        ReflectionUtils.findAndInvokeMethod(myAgent, "stockUpWater", 60);
        LoggerUtils.log(logger, Logger.INFO, "Factory stocks up some water");
    }
}
