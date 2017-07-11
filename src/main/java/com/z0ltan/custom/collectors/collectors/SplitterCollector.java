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
import java.util.stream.Collectors;

import com.z0ltan.custom.collectors.types.Splitter;
import com.z0ltan.custom.collectors.types.Wrapper;

public class SplitterCollector {
	public static Collector<Wrapper, List<Wrapper>, Splitter<Integer>> toSplitter() {
		return new SplitterCollectorImpl();
	}

	static class SplitterCollectorImpl implements Collector<Wrapper, List<Wrapper>, Splitter<Integer>> {
		@Override
		public Supplier<List<Wrapper>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BiConsumer<List<Wrapper>, Wrapper> accumulator() {
			return List::add;
		}

		@Override
		public BinaryOperator<List<Wrapper>> combiner() {
			return (acc, xs) -> {
				acc.addAll(xs);
				return acc;
			};
		}

		@Override
		public Function<List<Wrapper>, Splitter<Integer>> finisher() {
			return (wrapperList) -> {
				// get a combined list of values
				final List<Integer> combinedValues = wrapperList.stream().flatMap((w) -> w.values().stream())
						.collect(Collectors.toList());

				int middle = combinedValues.size() / 2;

				final List<Integer> firstHalf = combinedValues.stream().limit(middle).collect(Collectors.toList());
				final List<Integer> secondHalf = combinedValues.stream().skip(middle).collect(Collectors.toList());

				return new Splitter<>(firstHalf, secondHalf);
			};
		}

		@Override
		public Set<java.util.stream.Collector.Characteristics> characteristics() {
			return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT));
		}
	}
}
