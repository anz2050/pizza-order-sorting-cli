package com.anz.sortingcli.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import com.google.common.collect.ImmutableMap;

@Component
public class CliOptionManager {
	
	private static final Logger logger = LoggerFactory.getLogger(CliOptionManager.class);
	
	private Options options = new Options();
	private HelpFormatter helpFormatter;
	
	public void createOption() {
		Option helpOption = new Option("help", "print this message");
		Option sourceDirOption = new Option("srcDir", true, "Source directory (required)");
		Option destDirOption = new Option("destDir", true, "Destination directory (required)");
		
		options.addOption(helpOption);
		options.addOption(sourceDirOption);
		options.addOption(destDirOption);
	}
	
	public Map<String, File> parseCommandLine(String[] args) {
		CommandLineParser commandLineParser = new DefaultParser();
		Map<String, File> srcAndDestFolder = new HashMap<String, File>();
		CommandLine commandLine;
		try {
			commandLine = commandLineParser.parse(options, args);
			if (commandLine.hasOption("help")) {
				help();
			} else {
				if (commandLine.hasOption("srcDir") && commandLine.hasOption("destDir")) {
					String srcStr = commandLine.getOptionValue("srcDir");
					String destStr = commandLine.getOptionValue("destDir");
					checkCommandLineArgEquality(srcStr, destStr);
					try {
						srcAndDestFolder = checkFolderExistence(srcStr, destStr);
					} catch (IOException e) {
						logger.error("IOException: {}", e.getMessage());
						e.printStackTrace();
					}
				} else {
					help();
				}
			}
		} catch (ParseException pe) {
			logger.info("Failed to parse command line argument: Use --help for more detail {} ", pe);
			System.exit(-1);
		} catch (SecurityException se) {
			logger.info("Failed to create destination directory {} ", se);
			System.exit(-1);
		}
		return srcAndDestFolder;
	}
	
	private void checkCommandLineArgEquality(final String srcStr, final String destStr) {
		if (srcStr.equals(destStr)) {
			logger.info("Source directory should not be same as destination directory");
			System.exit(0);
		}
	}
	
	private Map<String, File> checkFolderExistence(final String srcStr, final String destStr)
			throws IOException, SecurityException {
		final File srcDir = new File(srcStr);
		final File destDir = new File(destStr);
		if (!srcDir.isDirectory()) {
			logger.info("Source directory does not exist: {}. Use --help for more detail ", srcDir);
			System.exit(0);
		}
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		return ImmutableMap.of("srcDir", srcDir, "destDir", destDir);
	}
	
	private void help() {
		helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("Pizza Order Sorting CLI: {}", options);
		System.exit(0);
	}
}