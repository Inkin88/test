import java.time.LocalDateTime;
import java.util.List;

public interface Filter {

    List<Flight> departureAfterTime(List<Flight> flights, LocalDateTime dateTime);

    List<Flight> arrivalTimeAfterDeparture(List<Flight> flights);

    List<Flight> betweenFlightTime(List<Flight> flights, long hours);
}
