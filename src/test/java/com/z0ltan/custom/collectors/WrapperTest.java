package com.z0ltan.custom.collectors;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.z0ltan.custom.collectors.collectors.WrapperCollector;
import com.z0ltan.custom.collectors.types.Wrapper;

public class WrapperTest {
	@Test
	public void testWrapper() {
		final Wrapper initial = new Wrapper(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		final Wrapper original = new Wrapper(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		final Wrapper modified = initial.stream().map((elem) -> {
			if (elem % 2 != 0) {
				return elem * 2;
			} else {
				return elem;
			}
		}).collect(new WrapperCollector());

		// the original object is, of course, unchanged
		assertEquals(initial, original);
		assertEquals(modified, new Wrapper(Arrays.asList(2, 2, 6, 4, 10, 6, 14, 8, 18, 10)));
	}
}
