package edu.upc.fib.sid.behavours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class FactoryOneShotBehaviour extends OneShotBehaviour {
    public void action() {
        AID riverAID = getRiverAID();

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(riverAID);
        msg.setContent("Need water");
        myAgent.send(msg);
    }

    private AID getRiverAID() {
        DFAgentDescription template = new DFAgentDescription();
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
