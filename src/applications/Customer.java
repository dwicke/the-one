package applications;

import core.*;
import movement.Bondsman;
import movement.CustomerMovement;

public class Customer extends Application {




    private final int WANDER = 0;
    private final int WAITING_FOR_RIDE = 1;
    private final int PICKED_UP = 2;

    int state;


    double bountyRate = 0.0;
    double baseBounty = 100.0;
    double genRate = 0.01;




    /* Bounty rate */
    public static final String CUSTOMER_BOUNTY_RATE = "bountyRate";
    /* Base bounty */
    public static final String CUSTOMER_BASE_BOUNTY = "baseBounty";
    /* Rate at which the customer needs a ride */
    public static final String CUSTOMER_GENERATION_RATE = "generationRate";


    public Customer(Customer a) {
        super(a);
        this.state = a.state;
    }
    public Customer(Settings s) {
        state = 0;

        this.baseBounty = s.getDouble(CUSTOMER_BASE_BOUNTY);
        this.bountyRate = s.getDouble(CUSTOMER_BOUNTY_RATE);
        this.genRate = s.getDouble(CUSTOMER_GENERATION_RATE);
    }




    @Override
    public Message handle(Message msg, DTNHost host) {



        return msg;
    }

    @Override
    public void update(DTNHost host) {

        switch (state) {
            case WANDER:
                CustomerMovement mvt = (CustomerMovement) host.getMovement();

                break;
            case WAITING_FOR_RIDE:
                break;
            case PICKED_UP:
                break;
        }

    }

    @Override
    public Application replicate() {
        return new Customer(this);
    }

}
