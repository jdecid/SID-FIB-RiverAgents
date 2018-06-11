package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behavours.FactoryOneShotBehaviour;

public class FactoryAgent extends BaseAgent {

    @Override
    protected void setup() {
        this.name = "Factory";
        this.type = "Factory";
        this.behaviour = new FactoryOneShotBehaviour();

        super.setup();
    }
}
