package com.z0ltan.custom.collectors.collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.z0ltan.custom.collectors.types.Wrapper;

public class WrapperCollector implements Collector<Integer, List<Integer>, Wrapper> {
	public Supplier<List<Integer>> supplier() {
		return ArrayList::new;
	}

	public BiConsumer<List<Integer>, Integer> accumulator() {
		return List::add;
	}

	public BinaryOperator<List<Integer>> combiner() {
		return (acc, xs) -> {
			acc.addAll(xs);
			return acc;
		};
	}

	public Function<List<Integer>, Wrapper> finisher() {
		return (acc) -> new Wrapper(acc);
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT));
	}
}
