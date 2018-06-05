package com.github.jcornaz.collekt;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class QueryBenchmark {

    @State(Scope.Benchmark)
    public static class Input {

        @Param({"100", "1000", "10000", "100000", "1000000", "10000000", "100000000", "1000000000"})
        private int size;

        @Param
        private ListImplementation implementation;

        private PersistentList<Integer> source;

        private int randomIndex;

        private Random rng = new Random();

        @Setup(Level.Iteration)
        public void chooseRandomIndex() {
            randomIndex = rng.nextInt(size - 1);
        }

        @Setup(Level.Iteration)
        public void createSource() {
            source = PersistentCollectionKt.empty(implementation.factory);
            for (int i = 0; i < size; ++i) {
                source = source.plus(i);
            }
        }

        int getSize() {
            return size;
        }

        int getRandomIndex() {
            return randomIndex;
        }

        PersistentList<Integer> getSource() {
            return source;
        }
    }


    @Benchmark
    public int measureSize(Input input) {
        return input.getSource().getSize();
    }

    @Benchmark
    public boolean measureIsEmpty(Input input) {
        return input.getSource().isEmpty();
    }

    @Benchmark
    @Measurement(iterations = 30)
    public int measureRandomAccess(Input input) {
        return input.getSource().get(input.getRandomIndex());
    }

    @Benchmark
    public int measureGetFirst(Input input) {
        return input.getSource().get(0);
    }

    @Benchmark
    public int measureGetLast(Input input) {
        return input.getSource().get(input.getSize() - 1);
    }

    @Benchmark
    public void measureIteration(Input input, Blackhole blackhole) {
        Iterator<Integer> iterator = input.getSource().iterator();
        while (iterator.hasNext()) {
            blackhole.consume(iterator.next());
        }
    }
}
