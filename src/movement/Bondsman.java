package movement;

import core.Coord;
import core.DTNSim;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Bondsman {

    public static final String BONDSMAN_CONTROL_SYSTEM_NR = "bondsmanControlSystemNr";
    private static HashMap<Integer, Bondsman> systems;

    private HashMap<Integer, BountyHunterMovement> bountyHunters;
    private HashMap<Integer, CustomerMovement> customers;
    private List<Coord> busStops;



    static {
        DTNSim.registerForReset(BusControlSystem.class.getCanonicalName());
        reset();
    }

    /**
     * Creates a new instance of BusControlSystem without any travelers or
     * bountyHunters
     */
    private Bondsman() {
        bountyHunters = new HashMap<Integer, BountyHunterMovement>();
        customers = new HashMap<Integer, CustomerMovement>();
    }

    public static void reset() {
        systems = new HashMap<Integer, Bondsman>();
    }

    /**
     * Called by bountyHunters belonging to this system every time the bus has stopped.
     * It calls every passengers enterBus() method so that the passengers can
     * enter the bus if they want to.
     * @param hunterID Unique identifier of the bus
     * @param hunterStop Coordinates of the bus stop
     * @param nextPath The path to the next stop
     */
    public void bountyHunterHasStopped(int hunterID, Coord hunterStop, Path nextPath) {
        Iterator<CustomerMovement> iterator = customers.values().
                iterator();
        while (iterator.hasNext()) {
            CustomerMovement traveller = iterator.next();
            if (traveller.getLocation() != null) {
                if ((traveller.getLocation()).equals(hunterStop)) {
                    Path path = new Path(nextPath);
                    traveller.enter(path);
                }
            }
        }
    }

    /**
     * Returns a reference to a BusControlSystem with ID provided as parameter.
     * If a system does not already exist with the requested ID, a new one is
     * created.
     * @param systemID unique ID of the system
     * @return The bus control system with the provided ID
     */
    public static Bondsman getBusControlSystem(int systemID) {
        Integer id = new Integer(systemID);

        if (systems.containsKey(id)) {
            return systems.get(id);
        } else {
            Bondsman bcs = new Bondsman();
            systems.put(id, bcs);
            return bcs;
        }
    }

    /**
     * Registers a bus to be part of a bus control system
     * @param hunter The bus to register
     */
    public void registerBountyHunter(BountyHunterMovement hunter) {
        bountyHunters.put(hunter.getID(), hunter);
    }

    /**
     * Registers a traveller/passenger to be part of a bus control system
     * @param traveller The traveller to register
     */
    public void registerTraveller(CustomerMovement traveller) {
        customers.put(traveller.getID(), traveller);
    }



}
