import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Floor 0: 1 bike spot, 1 car spot
        List<ParkingSpot> floor0Spots = new ArrayList<>();
        floor0Spots.add(new ParkingSpot("0-B1", VehicleType.BIKE));
        floor0Spots.add(new ParkingSpot("0-C1", VehicleType.CAR));

        // Floor 1: 1 car spot, 1 truck spot
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new ParkingSpot("1-C1", VehicleType.CAR));
        floor1Spots.add(new ParkingSpot("1-T1", VehicleType.TRUCK));

        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(new ParkingFloor(0, floor0Spots));
        floors.add(new ParkingFloor(1, floor1Spots));

        ParkingLot lot = new ParkingLot(floors);

        Ticket carTicket = lot.parkVehicle(new Car("JH05-1234"));
        Ticket bikeTicket = lot.parkVehicle(new Bike("JH05-9999"));
        System.out.println("Car parked at spot: " + carTicket.getSpot().getId());
        System.out.println("Bike parked at spot: " + bikeTicket.getSpot().getId());

        double fee = lot.unparkVehicle(carTicket.getTicketId());
        System.out.println("Car fee: " + fee);

        // Test the error cases
        lot.parkVehicle(new Truck("JH05-7777"));
        try {
            lot.parkVehicle(new Truck("JH05-8888"));   // no second truck spot
        } catch (ParkingFullException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}