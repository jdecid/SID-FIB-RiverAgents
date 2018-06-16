package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behavours.TreatmentPlantBehaviour;

public class TreatmentPlantAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = "TreatmentPlant";
        this.type = "TreatmentPlant";
        this.behaviour = new TreatmentPlantBehaviour(this);

        super.setup();
    }
}
