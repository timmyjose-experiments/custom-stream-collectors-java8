package com.z0ltan.custom.collectors.types;

import java.util.Set;

/**
 * Another arbitrary type.
 * 
 * @author z0ltan
 *
 */
public class Bar {
	private Set<Integer> ids;
	private Set<String> names;

	public Bar(final Set<Integer> ids, final Set<String> names) {
		this.ids = ids;
		this.names = names;
	}

	@Override
	public String toString() {
		return "Bar { ids = " + ids + ", names = " + names + "}";
	}

	@Override
	public int hashCode() {
		return (this.ids.hashCode() + this.names.hashCode()) % 31;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Bar)) {
			return false;
		}

		Bar b = (Bar) other;

		return b.ids.equals(this.ids) && b.names.equals(this.names);
	}
}
