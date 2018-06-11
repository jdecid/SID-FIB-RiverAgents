package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behavours.RiverCyclicBehaviour;

public class RiverAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = "Besòs River";
        this.type = "River";
        this.behaviour = new RiverCyclicBehaviour();

        super.setup();
    }
}
