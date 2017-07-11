package com.z0ltan.custom.collectors;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.z0ltan.custom.collectors.collectors.FooToBarCollector;
import com.z0ltan.custom.collectors.types.Bar;
import com.z0ltan.custom.collectors.types.Foo;

public class FooToBarTest {
	private Set<Foo> foos;
	private Set<Foo> foosCopy;

	@Before
	public void setup() {
		foos = new HashSet<>();
		foos.addAll(Arrays.asList(new Foo(1, "Hello"), new Foo(2, "World"), new Foo(3, "We"), new Foo(4, "Meet"),
				new Foo(5, "Again")));

		foosCopy = new HashSet<>();
		foosCopy.addAll(Arrays.asList(new Foo(1, "Hello"), new Foo(2, "World"), new Foo(3, "We"), new Foo(4, "Meet"),
				new Foo(5, "Again")));

	}

	@Test
	public void testFooToBar() {
		final Bar bar = foos.stream().collect(FooToBarCollector.toBar());

		assertEquals(foos, foosCopy);

		final Set<Integer> ids = new HashSet<>();
		final Set<String> names = new HashSet<>();

		ids.addAll(Arrays.asList(1, 2, 3, 4, 5));
		names.addAll(Arrays.asList("Hello", "World", "We", "Meet", "Again"));

		assertEquals(bar, new Bar(ids, names));
	}

	@Test
	public void testFooFilterBar() {
		final Bar bar = foos.stream().filter((f) -> f.id() % 2 != 0).collect(FooToBarCollector.toBar());

		final Set<Integer> filteredIds = new HashSet<>();
		final Set<String> filteredNames = new HashSet<>();

		filteredIds.addAll(Arrays.asList(1, 3, 5));
		filteredNames.addAll(Arrays.asList("Hello", "We", "Again"));

		assertEquals(foos, foosCopy);
		assertEquals(bar, new Bar(filteredIds, filteredNames));
	}

	@Test
	public void testFooFilterMapBar() {
		final Bar bar = foos.stream().filter((f) -> f.id() % 2 == 0).map((o) -> {
			Foo foo = new Foo();
			foo.id(o.id());
			foo.name(o.name().toUpperCase());

			return foo;
		}).collect(FooToBarCollector.toBar());

		final Set<Integer> filteredIds = new HashSet<>();
		final Set<String> filteredNames = new HashSet<>();

		filteredIds.addAll(Arrays.asList(2, 4));
		filteredNames.addAll(Arrays.asList("WORLD", "MEET"));

		assertEquals(foos, foosCopy);
		assertEquals(bar, new Bar(filteredIds, filteredNames));
	}
}
