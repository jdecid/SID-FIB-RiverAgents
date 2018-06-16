package edu.upc.fib.sid;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class DFUtils {
    public static AID getRiverAID(Agent agent) {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName(Constants.BESOS);
        serviceDescription.setType(Constants.RIVER);
        return DFUtils.performAIDQuery(agent, serviceDescription);
    }

    public static AID getTreatmentPlantAID(Agent agent) {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Constants.TREATMENT_PLANT);
        return DFUtils.performAIDQuery(agent, serviceDescription);
    }

    private static AID performAIDQuery(Agent agent, ServiceDescription serviceDescription) {
        DFAgentDescription template = new DFAgentDescription();
        template.addServices(serviceDescription);

        DFAgentDescription[] results;
        try {
            results = DFService.search(agent, template, new SearchConstraints());
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
