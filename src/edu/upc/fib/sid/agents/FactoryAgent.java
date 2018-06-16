package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behavours.FactoryBehaviour;

public class FactoryAgent extends BaseAgent {

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args.length > 0) {
            this.name = args[0].toString();
            this.type = "Factory";
            this.behaviour = new FactoryBehaviour(this, 5000);
            super.setup();
        } else {
            System.err.println("Factories requires a name as first parameter");
            this.doDelete();
        }
    }
}
