package edu.upc.fib.sid.helpers;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.util.leap.Iterator;
import org.apache.jena.base.Sys;

import static jade.tools.sniffer.Agent.i;

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
                return results[0].getName();
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTypeByAID(Agent agent, AID aid) {
        DFAgentDescription[] results;
        try {
            results = DFService.search(agent, new DFAgentDescription());
            for (DFAgentDescription result : results) {
                if (result.getName().getName().equals(aid.getName())) {
                    Iterator services = result.getAllServices();
                    if (services.hasNext()) {
                        ServiceDescription element = (ServiceDescription) services.next();
                        return element.getType();
                    }
                }
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        } return null;
    }
}
