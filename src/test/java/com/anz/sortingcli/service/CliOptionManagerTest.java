package com.anz.sortingcli.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Test;

public class CliOptionManagerTest {
	
	

	@Test(expected = UnrecognizedOptionException.class)
    public void testBadOption() throws ParseException {
        Options o = new Options().addOption("srcDir", true, "Source Dir");

        String[] arguments = { "-orange", "5" };
        new DefaultParser().parse(o, arguments);
    }
	
	@Test
    public void testArguments() throws ParseException {
        Options o = new Options().addOption("srcDir", true, "Source Dir");

        String[] arguments = {"--", "--srcDir", "c:\\tmp" };
        CommandLine c = new DefaultParser().parse(o, arguments);

        assertArrayEquals(new String[] { "--srcDir", "c:\\tmp" }, c.getArgs());
    }

}
