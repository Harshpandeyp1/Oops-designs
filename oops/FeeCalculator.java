import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {

    public double calculate(Ticket ticket, LocalDateTime exitTime) {
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = Math.max(1, (long) Math.ceil(minutes / 60.0));   // round up, minimum 1 hour
        return hours * rateFor(ticket.getVehicle().getType());
    }

    private double rateFor(VehicleType type) {
        switch (type) {
            case BIKE:  return 10;
            case CAR:   return 20;
            case TRUCK: return 50;
            default:    throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}