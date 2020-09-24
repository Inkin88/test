import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(FlightBuilder.createFlights());
        FlightFilter filter = new FlightFilter();
        System.out.println(filter.departureAfterTime(FlightBuilder.createFlights(), LocalDateTime.now()));
        System.out.println(filter.arrivalTimeAfterDeparture(FlightBuilder.createFlights()));
    }
}
