package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.helpers.Constants;
import edu.upc.fib.sid.behaviours.treatmentPlant.TreatmentPlantMainBehaviour;

public class TreatmentPlantAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = "EDAR";
        this.type = Constants.TREATMENT_PLANT;
        this.behaviour = new TreatmentPlantMainBehaviour(this);

        super.setup();
    }
}
