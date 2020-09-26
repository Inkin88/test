package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> list = FlightBuilder.createFlights();
        System.out.println(list);
        FlightFilter filter = new FlightFilter();
        System.out.println(filter.toFilter(list, FlightPredicate.isDepartureAfterTime(LocalDateTime.now())));
        System.out.println(filter.toFilter(list, FlightPredicate.isArrivalDateBeforeDeparture()));
        System.out.println(filter.toFilter(list, FlightPredicate.isSpentTimeOnGround(2)));
    }
}
