package edu.phystech.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Functional {

    public static <T, R> List<R> map(List<? extends T> collection, Function<? super T, R> function) {
        List<R> result = new ArrayList<>(collection.size());
        for (T item : collection) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static <T> T reduce(List<T> collection, BinaryOperator<T> operator, T identity) {
        T accumulator = identity;
        for (T item : collection) {
            accumulator = operator.apply(accumulator, item);
        }
        return accumulator;
    }
}




