package com.z0ltan.custom.collectors.collectors;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.z0ltan.custom.collectors.types.Bar;
import com.z0ltan.custom.collectors.types.Foo;

/**
 * A more comprehensive collector type demonstrating how a stream of one
 * arbitrary type (Foo in this case) can be converted into a stream of another
 * arbitrary type (Bar in this case).
 * 
 * @author z0ltan
 *
 */
public class FooToBarCollector {
	public static Collector<Foo, Set<Foo>, Bar> toBar() {
		return new FooToBarCollectorImpl(HashSet::new, Set::add, (acc, set) -> {
			acc.addAll(set);
			return acc;
		});
	}

	static class FooToBarCollectorImpl implements Collector<Foo, Set<Foo>, Bar> {
		private Supplier<Set<Foo>> supplier;
		private BiConsumer<Set<Foo>, Foo> accumulator;
		private BinaryOperator<Set<Foo>> combiner;

		public FooToBarCollectorImpl(Supplier<Set<Foo>> supplier, BiConsumer<Set<Foo>, Foo> accumulator,
				BinaryOperator<Set<Foo>> combiner) {
			this.supplier = supplier;
			this.accumulator = accumulator;
			this.combiner = combiner;
		}

		@Override
		public Supplier<Set<Foo>> supplier() {
			return this.supplier;
		}

		@Override
		public BiConsumer<Set<Foo>, Foo> accumulator() {
			return this.accumulator;
		}

		@Override
		public BinaryOperator<Set<Foo>> combiner() {
			return this.combiner;
		}

		@Override
		public Function<Set<Foo>, Bar> finisher() {
			return (foos) -> {
				final Set<Integer> ids = new HashSet<>();
				final Set<String> names = new HashSet<>();

				for (Foo foo : foos) {
					ids.add(foo.id());
					names.add(new String(foo.name()));
				}

				return new Bar(ids, names);
			};
		}

		@Override
		public Set<java.util.stream.Collector.Characteristics> characteristics() {
			return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
		}
	}
}
