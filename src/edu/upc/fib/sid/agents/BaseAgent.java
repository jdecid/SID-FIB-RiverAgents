package edu.upc.fib.sid.agents;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

abstract class BaseAgent extends Agent {
    protected String name = "Base";
    protected String type = "Agent";

    protected void setup() {
        ServiceDescription sd = new ServiceDescription();
        sd.setName(name);
        sd.setType(type);

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);
    }
}
