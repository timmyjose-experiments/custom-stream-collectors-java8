package com.z0ltan.custom.collectors.types;

/**
 * An arbitrary type.
 * 
 * @author z0ltan
 *
 */
public class Foo {
	private int id;
	private String name;
	
	public Foo() {}

	public Foo(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public int id() {
		return this.id;
	}
	
	public void id(final int id) {
		this.id = id;
	}

	public String name() {
		return this.name;
	}
	
	public void name(final String name) {
		this.name = name;
	}
	
	

	@Override
	public String toString() {
		return "Foo { id = " + id + ", name = " + name + "}";
	}

	@Override
	public int hashCode() {
		return (this.id + this.name.hashCode()) % 31;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Foo)) {
			return false;
		}

		Foo f = (Foo) other;

		return f.id == this.id && f.name.equals(this.name);
	}
}
