package com.example.notifier.function;

@FunctionalInterface
public interface TriFunction<T, R, V> {

    T apply(R r, V v);
}
