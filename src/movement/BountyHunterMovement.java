package movement;

import core.Coord;
import core.Settings;
import movement.map.MapNode;

import java.util.LinkedList;
import java.util.List;

public class BountyHunterMovement extends ShortestPathMapBasedMovement{



    private Bondsman controlSystem;
    private int id;
    private static int nextID = 0;
    private boolean startMode;
    private List<Coord> stops;

    /**
     * Creates a new instance of BusMovement
     * @param settings
     */
    public BountyHunterMovement(Settings settings) {
        super(settings);
        int bcs = settings.getInt(Bondsman.BONDSMAN_CONTROL_SYSTEM_NR);
        controlSystem = Bondsman.getBusControlSystem(bcs);
        this.id = nextID++;
        controlSystem.registerBountyHunter(this);
        startMode = true;
        stops = new LinkedList<Coord>();
    }

    /**
     * Create a new instance from a prototype
     * @param proto
     */
    public BountyHunterMovement(BountyHunterMovement proto) {
        super(proto);
        this.controlSystem = proto.controlSystem;
        this.id = nextID++;
        controlSystem.registerBountyHunter(this);
        startMode = true;
    }

    @Override
    public Coord getInitialLocation() {
        return (super.getInitialLocation()).clone();
    }

    @Override
    public Path getPath() {
        Coord lastLocation = (super.getLastLocation()).clone();
        Path path = super.getPath();
        if (!startMode) {
            controlSystem.bountyHunterHasStopped(id, lastLocation, path);
        }
        startMode = false;
        return path;
    }

    @Override
    public BountyHunterMovement replicate() {
        return new BountyHunterMovement(this);
    }

    /**
     * Returns unique ID of the bus
     * @return unique ID of the bus
     */
    public int getID() {
        return id;
    }
}
