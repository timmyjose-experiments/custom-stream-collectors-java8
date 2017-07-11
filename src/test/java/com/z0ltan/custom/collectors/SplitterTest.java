package com.z0ltan.custom.collectors;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.z0ltan.custom.collectors.collectors.SplitterCollector;
import com.z0ltan.custom.collectors.types.Splitter;
import com.z0ltan.custom.collectors.types.Wrapper;

public class SplitterTest {
	@Test
	public void testSplitter() {
		final List<Wrapper> wrappers = Arrays.asList(new Wrapper(Arrays.asList(1, 2, 3, 4, 5)),
				new Wrapper(Arrays.asList(6, 7, 8, 9, 10)), new Wrapper(Arrays.asList(11, 12, 13, 14, 15)));

		final List<Wrapper> original = Arrays.asList(new Wrapper(Arrays.asList(1, 2, 3, 4, 5)),
				new Wrapper(Arrays.asList(6, 7, 8, 9, 10)), new Wrapper(Arrays.asList(11, 12, 13, 14, 15)));

		final Splitter<Integer> splitValue = wrappers.stream().collect(SplitterCollector.toSplitter());

		assertEquals(wrappers, original);
		assertEquals(splitValue,
				new Splitter<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15)));
	}
}
