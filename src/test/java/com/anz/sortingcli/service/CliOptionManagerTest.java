package com.anz.sortingcli.service;

import static org.junit.Assert.*;

import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Before;
import org.junit.Test;

public class CliOptionManagerTest {
	
	private CommandLineParser commandLineParser;
	private Options options;
	
	@Before
	public void setup() {
		commandLineParser = new DefaultParser();
		options = new Options();
	}
	
	@Test
	public void testGetOptionProperties() throws Exception {
		final String[] args = new String[] { "-srcDir", "c:\\tmp", "-destDir", "c:\\tmp\\out" };
		
		Option sourceDirOption = new Option("srcDir", true, "Source directory (required)");
		Option destDirOption = new Option("destDir", true, "Destination directory (required)");
		 options.addOption(sourceDirOption);
		 options.addOption(destDirOption);
		

        final CommandLine cl = commandLineParser.parse(options, args);
        final Properties props = cl.getOptionProperties("srcDir");

        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 1, props.size());
        
        assertEquals("srcDir", "c:\\tmp", cl.getOptionValue("srcDir"));
        assertEquals("destDir", "c:\\tmp\\out", cl.getOptionValue("destDir"));
	}
	
	
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
