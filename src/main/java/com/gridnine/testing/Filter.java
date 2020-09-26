package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;

public interface Filter<T> {

    List<T> toFilter(List<T> list, Predicate<T>... predicate);

}
