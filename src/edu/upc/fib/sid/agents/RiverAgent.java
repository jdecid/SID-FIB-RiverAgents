package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.helpers.Constants;
import edu.upc.fib.sid.behaviours.river.RiverBehaviour;

public class RiverAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = Constants.BESOS;
        this.type = Constants.RIVER;
        addBehaviour(new RiverBehaviour());

        super.setup();
    }
}
