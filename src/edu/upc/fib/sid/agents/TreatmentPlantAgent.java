package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.helpers.Constants;
import edu.upc.fib.sid.behaviours.treatmentPlant.TreatmentPlantMainBehaviour;
import edu.upc.fib.sid.models.WaterTank;

public class TreatmentPlantAgent extends BaseAgent {
    private WaterTank wasteWaterTank;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args.length > 0) {
            this.name = Constants.EDAR_BESOS;
            this.type = Constants.TREATMENT_PLANT;
            addBehaviour(new TreatmentPlantMainBehaviour(this));
            wasteWaterTank = new WaterTank(Integer.parseInt(args[0].toString()));
            super.setup();
        } else {
            System.err.println("Treatment plant requires tank capacity as a parameter");
            this.doDelete();
        }
    }

    public WaterTank getWasteWaterTank() {
        return wasteWaterTank;
    }
}
