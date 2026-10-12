import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final Map<String, Ticket> activeTickets = new HashMap<>();
    private final FeeCalculator feeCalculator = new FeeCalculator();

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findFreeSpot(vehicle.getType());
            if (spot != null) {
                spot.park(vehicle);
                Ticket ticket = new Ticket(vehicle, spot);
                activeTickets.put(ticket.getTicketId(), ticket);
                return ticket;
            }
        }
        throw new ParkingFullException("No free spot for " + vehicle.getType());
    }

    public double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new InvalidTicketException("Invalid ticket: " + ticketId);
        }
        LocalDateTime now = LocalDateTime.now();
        ticket.setExitTime(now);
        double fee = feeCalculator.calculate(ticket, now);
        ticket.getSpot().unpark();
        activeTickets.remove(ticketId);
        return fee;
    }
}