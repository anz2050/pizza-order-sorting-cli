package com.anz.sortingcli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anz.sortingcli.service.CliOptionManager;
import com.anz.sortingcli.service.FileProcessor;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class SortingCliApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(SortingCliApplication.class);
	
	private static final String SRC_DIR = "srcDir";
	private static final String DEST_DIR = "destDir";
	
	@Autowired
	private CliOptionManager cliOptionManager;
	
	@Autowired
	private FileProcessor fileProcessor;

	public static void main(String[] args) {
		SpringApplication.run(SortingCliApplication.class, args);
	}

	@Override
	public void run(String... commandLineArgs) throws Exception {
		cliOptionManager.createOption();
	    final Map<String, File> srcAndDestFolder = cliOptionManager.parseCommandLine(commandLineArgs);
	    
	    fileProcessor.processFile(srcAndDestFolder.get(SRC_DIR), srcAndDestFolder.get(DEST_DIR));
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
