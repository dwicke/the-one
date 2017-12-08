package movement;

import core.Coord;
import core.Settings;
import movement.map.DijkstraPathFinder;
import movement.map.MapNode;
import movement.map.SimMap;

import java.util.List;

public class CustomerMovement extends MapBasedMovement {

    private Path nextPath;
    private Coord location;
    private Coord latestBusStop;
    private Bondsman controlSystem;
    private int id;

    private Coord startLocation;
    private Coord endLocation;


    private static int nextID = 0;



    /**
     * Creates a BusTravellerModel
     * @param settings
     */
    public CustomerMovement(Settings settings) {
        super(settings);
        int bcs = settings.getInt(Bondsman.BONDSMAN_CONTROL_SYSTEM_NR);
        controlSystem = Bondsman.getBondsmanControlSystem(bcs);
        id = nextID++;
        controlSystem.registerTraveller(this);
        nextPath = new Path();

    }

    /**
     * Creates a BusTravellerModel from a prototype
     * @param proto
     */
    public CustomerMovement(CustomerMovement proto) {
        super(proto);

        controlSystem = proto.controlSystem;
        if (proto.location != null) {
            location = proto.location.clone();
        }
        nextPath = proto.nextPath;
        id = nextID++;
        controlSystem.registerTraveller(this);

    }







    @Override
    public Path getPath() {

//        if (state == STATE_WAITING) {
//            return null;
//        } else if (state == STATE_ENTER) {
//            List<Coord> coords = nextPath.getCoords();
//            location = (coords.get(coords.size() - 1)).clone();
//            return nextPath;
//        }

        return null;
    }


    @Override
    public MapBasedMovement replicate() {
        return new CustomerMovement(this);
    }



    /**
     * Get the location where the bus is located when it has moved its path
     * @return The end point of the last path returned
     */
    public Coord getLocation() {
        if (location == null) {
            return null;
        }
        return location.clone();
    }

    public int getID() {
        return id;
    }



    public void enter(Path path) {


    }





}
