public class ParkingSpot {
    private final String id;
    private final VehicleType type;
    private Vehicle parkedVehicle;   // null means the spot is empty

    public ParkingSpot(String id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

    public boolean canFit(Vehicle vehicle) {
        return isFree() && vehicle.getType() == type;
    }

    public void park(Vehicle vehicle) {
        if (!canFit(vehicle)) {
            throw new IllegalStateException("Spot " + id + " cannot fit this vehicle");
        }
        parkedVehicle = vehicle;
    }

    public void unpark() {
        parkedVehicle = null;
    }

    public String getId() { return id; }
    public VehicleType getType() { return type; }
}