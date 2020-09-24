import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightFilter implements Filter {

    @Override
    public List<Flight> departureAfterTime(List<Flight> flights, LocalDateTime dateTime) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            boolean check = true;
            List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                if (segment.getDepartureDate().isBefore(dateTime)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                result.add(flight);
            }
        }
        return result;
    }

    @Override
    public List<Flight> arrivalTimeAfterDeparture(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            boolean check = true;
            List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                result.add(flight);
            }
        }
        return result;
    }

    @Override
    public List<Flight> betweenFlightTime(List<Flight> flights, long hours) {
        return null;
    }

    private long betweenTime(LocalDateTime arrivalTime, LocalDateTime departureTime) {
        return ChronoUnit.HOURS.between(arrivalTime, departureTime);
    }
}
