package com.z0ltan.custom.collectors.types;

import java.util.List;
import java.util.stream.Stream;

/**
 * A simple wrapper around a Collection.
 * 
 * @author z0ltan
 *
 */
public class Wrapper {
	List<Integer> values;

	public Wrapper() {
	}

	public Wrapper(final List<Integer> values) {
		this.values = values;
	}

	// convenience methods
	public Stream<Integer> stream() {
		return this.values.stream();
	}

	public List<Integer> values() {
		return this.values;
	}

	@Override
	public String toString() {
		return "Wrapper { values = " + values + " } ";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Wrapper)) {
			return false;
		}

		Wrapper o = (Wrapper) other;

		return o.values.equals(this.values);
	}
}
