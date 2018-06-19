package edu.upc.fib.sid.behaviours.contractNet;

import edu.upc.fib.sid.behaviours.treatmentPlant.TreatmentPlantPourWaterBehaviour;
import edu.upc.fib.sid.helpers.Globals;
import edu.upc.fib.sid.models.WaterTank;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;
import jade.util.Logger;

import java.util.Vector;
import java.util.logging.Level;

import static edu.upc.fib.sid.helpers.LoggerUtils.log;
import static edu.upc.fib.sid.helpers.ReflectionUtils.invokeMethod;

public class RequestPourWaterProposalInitiator extends ContractNetInitiator {
    private Logger logger = Logger.getMyLogger(this.getClass().getName());
    private int numFactories;

    public RequestPourWaterProposalInitiator(Agent agent, ACLMessage cfp) {
        super(agent, cfp);
        numFactories = Globals.FactoriesAIDs.size();
    }

    void handleFactory() {
        numFactories -= 1;
        if(numFactories <= 0)
            invokeMethod(myAgent, "setWaitingNegotiation", Boolean.FALSE);
    }

    @Override
    protected void handlePropose(ACLMessage propose, Vector acceptances) {
        ACLMessage reply = propose.createReply();
        if (propose.getContent().equals("")) {
            reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            reply.setContent("OK");
        } else {
            reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
            reply.setContent("Not OK");
        }
        myAgent.send(reply);
    }

    @Override
    protected void handleNotUnderstood(ACLMessage notUnderstood) {
        log(logger, Level.WARNING, "Factory didn't understood message");

        invokeMethod(myAgent, "setWaitingNegotiation", Boolean.FALSE);
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {
        invokeMethod(myAgent, "setWaitingNegotiation", Boolean.FALSE);
        log(logger, Level.FINE, "Factory rejected proposal");
        handleFactory();

    }

    @Override
    protected void handleInform(ACLMessage inform) {
        WaterTank waterTank = (WaterTank) invokeMethod(myAgent, "getWasteWaterTank");
        waterTank.addWater(50);

        invokeMethod(myAgent, "setWaitingClean", Boolean.TRUE);
        invokeMethod(myAgent, "setWaitingNegotiation", Boolean.FALSE);
        myAgent.addBehaviour(new TreatmentPlantPourWaterBehaviour(myAgent, 1000));
        handleFactory();
    }

    @Override
    protected void handleFailure(ACLMessage failure) {
        log(logger, Level.FINE, "Factory denies to send water");
        handleFactory();
    }
}
