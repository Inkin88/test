package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightFilter implements Filter<Flight> {

    @Override
    public List<Flight> toFilter(List<Flight> flights, Predicate<Flight>... predicates) {
        Predicate<Flight> predicate = Stream.of(predicates).reduce(Predicate::and).orElse(value -> true);
        return flights.stream().filter(predicate).collect(Collectors.toList());
    }
}
