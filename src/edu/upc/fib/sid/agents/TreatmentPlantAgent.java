package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.helpers.Constants;
import edu.upc.fib.sid.behaviours.treatmentPlant.TreatmentPlantMainBehaviour;

public class TreatmentPlantAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = Constants.EDAR_BESOS;
        this.type = Constants.TREATMENT_PLANT;
        addBehaviour(new TreatmentPlantMainBehaviour(this));

        super.setup();
    }
}
