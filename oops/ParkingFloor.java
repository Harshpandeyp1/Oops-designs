import java.util.List;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public ParkingSpot findFreeSpot(VehicleType type) {
        for (ParkingSpot spot : spots) {
            if (spot.isFree() && spot.getType() == type) {
                return spot;
            }
        }
        return null;   // no free spot on this floor
    }

    public int getFloorNumber() { return floorNumber; }
}