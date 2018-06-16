package edu.upc.fib.sid.behavours;

import edu.upc.fib.sid.Constants;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class FactoryBehaviour extends TickerBehaviour {
    private AID riverAID;

    public FactoryBehaviour(Agent agent, long period) {
        super(agent, period);
        this.riverAID = getRiverAID();
        if (this.riverAID == null) {
            System.err.println("Besòs river not found");
            // TODO: Throws unexpected error
            agent.doDelete();
        }
    }

    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(riverAID);
        msg.setContent("Need water");
        myAgent.send(msg);
    }

    private AID getRiverAID() {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName(Constants.BESOS);
        serviceDescription.setType(Constants.RIVER);
        template.addServices(serviceDescription);

        DFAgentDescription[] results;
        try {
            results = DFService.search(myAgent, template, new SearchConstraints());
            if (results.length > 0) {
                System.out.println(results[0].getName());
                return results[0].getName();
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return null;
    }
}
