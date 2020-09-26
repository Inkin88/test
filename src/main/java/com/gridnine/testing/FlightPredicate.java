package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.function.Predicate;

public class FlightPredicate {

    public static Predicate<Flight> isDepartureAfterTime(LocalDateTime ldt) {
        return flight -> flight.getSegments().
                stream().
                noneMatch(segment -> segment.getDepartureDate().isBefore(ldt));
    }

    public static Predicate<Flight> isArrivalDateBeforeDeparture() {
        return flight -> flight.getSegments().
                stream().
                noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }

    public static Predicate<Flight> isSpentTimeOnGround(long hours) {
        return flight -> {
            boolean result = true;
            long hoursCount = 0;
            for (int i = 0; i < (flight.getSegments().size() - 1); i++) {
                if (hoursCount > hours) {
                    break;
                }
                Duration duration = Duration.between(flight.getSegments().get(i).getArrivalDate(), flight.getSegments().get(i + 1).getDepartureDate());
                hoursCount = hoursCount + duration.toHours();
            }
            if (hoursCount > hours) {
                result = false;
            }
            return result;
        };
    }
}
