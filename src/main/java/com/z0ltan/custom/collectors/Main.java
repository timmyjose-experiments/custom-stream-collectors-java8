package com.z0ltan.custom.collectors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.z0ltan.custom.collectors.collectors.FooToBarCollector;
import com.z0ltan.custom.collectors.collectors.SplitterCollector;
import com.z0ltan.custom.collectors.collectors.WrapperCollector;
import com.z0ltan.custom.collectors.types.Bar;
import com.z0ltan.custom.collectors.types.Foo;
import com.z0ltan.custom.collectors.types.Splitter;
import com.z0ltan.custom.collectors.types.Wrapper;

public class Main {
	public static void main(String[] args) {
		wrapperDemo();

		splitterDemo();

		fooToBarDemo();

		fooFilterBarDemo();

		fooFilterMapBarDemo();
	}

	/**
	 * In this example, let's double all the odd elements of the wrapped List.
	 * 
	 */
	private static void wrapperDemo() {
		final Wrapper initial = new Wrapper(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		final Wrapper modified = initial.stream().map((elem) -> {
			if (elem % 2 != 0) {
				return elem * 2;
			} else {
				return elem;
			}
		}).collect(new WrapperCollector());

		// the original object is, of course, unchanged
		System.out.printf("[wrapperDemo] original wrapper = %s\n\n", initial);
		System.out.printf("[wrapperDemo] modified wrapper = %s\n\n\n\n", modified);
	}

	/**
	 * Let's take a stream of Wrapper values, and then collect into a Splitter
	 * instance
	 * 
	 */
	private static void splitterDemo() {
		final List<Wrapper> wrappers = Arrays.asList(new Wrapper(Arrays.asList(1, 2, 3, 4, 5)),
				new Wrapper(Arrays.asList(6, 7, 8, 9, 10)), new Wrapper(Arrays.asList(11, 12, 13, 14, 15)));

		final Splitter<Integer> splitValue = wrappers.stream().collect(SplitterCollector.toSplitter());

		System.out.printf("[splitterDemo] original wrappers = %s\n\n", wrappers);
		System.out.printf("[splitterDemo] splitValue = %s\n\n\n\n", splitValue);
	}

	/**
	 * Collect a stream of type Foo into a single instance of type Bar -
	 * demonstrating general collect into arbitrary types.
	 */
	private static void fooToBarDemo() {
		final Set<Foo> foos = new HashSet<>();
		foos.addAll(Arrays.asList(new Foo(1, "Hello"), new Foo(2, "World"), new Foo(3, "We"), new Foo(4, "We"),
				new Foo(5, "Meet"), new Foo(6, "Again")));

		final Bar bar = foos.stream().collect(FooToBarCollector.toBar());

		System.out.printf("[fooToBarDemo] original foos = %s\n\n", foos);
		System.out.printf("[fooToBarDemo] bar = %s\n\n\n\n", bar);
	}

	/**
	 * Filter a stream of Foo instances and collect into a Bar instance.
	 */
	private static void fooFilterBarDemo() {
		Set<Foo> foos = new HashSet<>();
		foos.addAll(Arrays.asList(new Foo(1, "Hello"), new Foo(2, "World"), new Foo(3, "We"), new Foo(4, "Meet"),
				new Foo(5, "Again")));

		final Bar bar = foos.stream().filter((f) -> f.id() % 2 != 0).collect(FooToBarCollector.toBar());

		System.out.printf("[fooFilterBarDemo] foos = %s\n\n", foos);
		System.out.printf("[fooFilterBarDemo] bar = %s\n\n\n\n", bar);
	}

	/**
	 * Filter and then map a stream of Foo instances, and finally collect into a
	 * Bar instance.
	 */
	private static void fooFilterMapBarDemo() {
		Set<Foo> foos = new HashSet<>();
		foos.addAll(Arrays.asList(new Foo(1, "Hello"), new Foo(2, "World"), new Foo(3, "We"), new Foo(4, "Meet"),
				new Foo(5, "Again")));

		final Bar bar = foos.stream().filter((f) -> f.id() % 2 == 0).map((o) -> {
			Foo foo = new Foo();
			foo.id(o.id());
			foo.name(o.name().toUpperCase());

			return foo;
		}).collect(FooToBarCollector.toBar());

		System.out.printf("[fooFilterMapBarDemo] foos = %s\n\n", foos);
		System.out.printf("[fooFilterMapBarDemo] bar = %s\n\n", bar);
	}
}
