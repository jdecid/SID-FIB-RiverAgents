package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.Constants;
import edu.upc.fib.sid.behavours.TreatmentPlantBehaviour;

public class TreatmentPlantAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = "EDAR";
        this.type = Constants.TREATMENT_PLANT;
        this.behaviour = new TreatmentPlantBehaviour(this);

        super.setup();
    }
}
