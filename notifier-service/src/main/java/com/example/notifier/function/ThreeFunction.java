package com.example.notifier.function;

@FunctionalInterface
public interface ThreeFunction<T, R, V> {

    T apply(R r, V v);
}
