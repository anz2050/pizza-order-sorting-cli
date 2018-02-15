package com.anz.sortingcli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SortingCliApplicationTest {

	@Test
	public void test_arguments() throws Exception {
		final String[] args = new String[] { "-srcDir", "c:\\tmp", "-destDir", "c:\\tmp\\out" };
		assertEquals(4, args.length);
	}
}
