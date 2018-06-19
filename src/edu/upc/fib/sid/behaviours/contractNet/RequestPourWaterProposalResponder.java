package edu.upc.fib.sid.behaviours.contractNet;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public class RequestPourWaterProposalResponder extends ContractNetResponder {
    public RequestPourWaterProposalResponder(Agent a, MessageTemplate mt) {
        super(a, mt);
    }

    @Override
    protected ACLMessage prepareResponse(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
        return super.prepareResponse(cfp);
    }

    @Override
    public void registerPrepareResultNotification(Behaviour b) {
        super.registerPrepareResultNotification(b);
    }

    @Override
    protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
        super.handleRejectProposal(cfp, propose, reject);
    }
}
