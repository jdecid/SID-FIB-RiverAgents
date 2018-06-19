package edu.upc.fib.sid.behaviours.contractNet;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

import java.util.Vector;

public class RequestPourWaterProposalInitiator extends ContractNetInitiator {
    public RequestPourWaterProposalInitiator(Agent a, ACLMessage cfp) {
        super(a, cfp);
    }

    @Override
    protected void handlePropose(ACLMessage propose, Vector acceptances) {
        super.handlePropose(propose, acceptances);
    }

    @Override
    protected void handleNotUnderstood(ACLMessage notUnderstood) {
        super.handleNotUnderstood(notUnderstood);
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {
        super.handleRefuse(refuse);
    }

    @Override
    protected void handleInform(ACLMessage inform) {
        super.handleInform(inform);
    }

    @Override
    protected void handleFailure(ACLMessage failure) {
        super.handleFailure(failure);
    }
}
