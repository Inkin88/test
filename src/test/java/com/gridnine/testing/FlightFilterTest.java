package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class FlightFilterTest {

    private final List<Flight> flightList = new ArrayList<>();
    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;
    private Flight flight5;
    private Flight flight6;
    private final FlightFilter filter = new FlightFilter();

    @Before
    public void setUp() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        flight1 = new Flight(Collections.singletonList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        flight2 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5))));
        flight3 = new Flight(Collections.singletonList(new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow)));
        flight4 = new Flight(Collections.singletonList(new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6))));
        flight5 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6))));
        flight6 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)),
                new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))));
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        flightList.add(flight5);
        flightList.add(flight6);
    }

    @Test
    public void checkDepartureAfterTime() {
        List<Flight> result = filter.toFilter(flightList, FlightPredicate.isDepartureAfterTime(LocalDateTime.now()));
        List<Flight> expected = Arrays.asList(flight1, flight2, flight4, flight5, flight6);
        Assert.assertThat(result, is(expected));
    }

    @Test
    public void checkArrivalDateBeforeDeparture() {
        List<Flight> result = filter.toFilter(flightList, FlightPredicate.isArrivalDateBeforeDeparture());
        List<Flight> expected = Arrays.asList(flight1, flight2, flight3, flight5, flight6);
        Assert.assertThat(result, is(expected));
    }

    @Test
    public void checkSpentTimeOnGround() {
        List<Flight> result = filter.toFilter(flightList, FlightPredicate.isSpentTimeOnGround(2));
        List<Flight> expected = Arrays.asList(flight1, flight2, flight3, flight4);
        Assert.assertThat(result, is(expected));
    }

    @Test
    public void checkAllPredicates() {
        List<Flight> result = filter.toFilter(flightList, FlightPredicate.isSpentTimeOnGround(2),
                FlightPredicate.isArrivalDateBeforeDeparture(), FlightPredicate.isDepartureAfterTime(LocalDateTime.now()));
        List<Flight> expected = Arrays.asList(flight1, flight2);
        Assert.assertThat(result, is(expected));
    }
}

