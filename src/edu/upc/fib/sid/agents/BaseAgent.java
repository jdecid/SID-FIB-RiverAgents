package edu.upc.fib.sid.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

abstract class BaseAgent extends Agent {
    String name;
    String type;
    Behaviour behaviour;

    protected void setup() {
        assert name != null && type != null && behaviour != null;

        ServiceDescription sd = new ServiceDescription();
        sd.setName(this.name);
        sd.setType(this.type);

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);

        try {
            DFService.register(this, dfd);
            this.addBehaviour(this.behaviour);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
