package com.anz.sortingcli.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FileProcessorTest {

	@Test
	public void testReadingFile() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		File sourceFile = new File(classLoader.getResource("input/sample_data_ordered.txt").getFile());

		assertTrue(sourceFile != null);
		assertTrue(sourceFile.exists());
		assertTrue(sourceFile.isFile());
		assertTrue(sourceFile.getName().equals("sample_data_ordered.txt"));

		File destinationDir = new File("output");
		assertTrue(destinationDir != null);
		assertFalse(destinationDir.isFile());

	}
}
