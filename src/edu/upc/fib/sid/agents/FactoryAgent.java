package edu.upc.fib.sid.agents;

import edu.upc.fib.sid.behaviours.factories.FactoryMainBehaviour;

public class FactoryAgent extends BaseAgent {

    @Override
    protected void setup() {
        Object[] args = getArguments();
        if (args.length > 1) {
            name = args[0].toString();
            type = "Factory";
            behaviour = new FactoryMainBehaviour(this, 10000);
            super.setup();
        } else {
            System.err.println("Factories require a name as first parameter, and tank capacity as second one");
            this.doDelete();
        }
    }

}
