package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.Constants;
import edu.upc.fib.sid.behavours.RiverBehaviour;

public class RiverAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = Constants.BESOS;
        this.type = Constants.RIVER;
        this.behaviour = new RiverBehaviour();

        super.setup();
    }
}
