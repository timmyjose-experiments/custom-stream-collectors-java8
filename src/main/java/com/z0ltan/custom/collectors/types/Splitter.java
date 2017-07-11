package com.z0ltan.custom.collectors.types;

import java.util.List;

public class Splitter<T> {
	private List<T> firstHalf;
	private List<T> secondHalf;

	public Splitter(final List<T> f, final List<T> s) {
		this.firstHalf = f;
		this.secondHalf = s;
	}

	@Override
	public String toString() {
		return "Splitter { firstHalf = " + firstHalf + ", " + "secondHalf = " + secondHalf + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Splitter)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		Splitter<T> o = (Splitter<T>) other;

		return o.firstHalf.equals(this.firstHalf) && o.secondHalf.equals(this.secondHalf);
	}
}
