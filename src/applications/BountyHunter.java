package applications;

import core.Application;
import core.DTNHost;
import core.Message;
import core.Settings;

public class BountyHunter extends Application {


    public BountyHunter(BountyHunter a) {
        super(a);
        super.setAppID(null);
        // then add set the parameters based on a
        // see PingApplication
        // will need to register with the bondsman
    }
    public BountyHunter(Settings s) {
        super.setAppID(null);
    }




    @Override
    public Message handle(Message msg, DTNHost host) {
        return msg;
    }

    @Override
    public void update(DTNHost host) {

        // here I need to decide what task to go after
        // and I need to keep track of when


    }

    @Override
    public Application replicate() {
        return new BountyHunter(this);
    }
}
