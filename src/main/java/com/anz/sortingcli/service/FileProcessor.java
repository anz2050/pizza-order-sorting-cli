package com.anz.sortingcli.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.anz.sortingcli.model.PizzaOrder;

@Component
public class FileProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(FileProcessor.class);
		
	public void processFile(File sourceDir, final File destinationDir) {

		File[] files = sourceDir.listFiles();

		for (File file : files) {
			String fileName = file.getName();
			List<PizzaOrder> pizzaOrderList = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				br.readLine(); // this will read the first line, I want to skip header line
				String sCurrentLine;
				logger.info("---------------- Before Sorting ---------------------");
				while ((sCurrentLine = br.readLine()) != null) {
					
					String[] splittedOrder = sCurrentLine.trim().split("\\s+");
					if (splittedOrder.length > 1) {
						PizzaOrder pizzaOrder = new PizzaOrder(splittedOrder[0].trim(), splittedOrder[1].trim());
						logger.info(splittedOrder[0] +"  "+ splittedOrder[1]);
						pizzaOrderList.add(pizzaOrder);
					}
				}
			} catch (IOException e) {
				logger.error("Issue in reading data from source file, {}", e.getMessage());
				e.printStackTrace();
			}
			sortPizzaOrderByName(pizzaOrderList);
			logger.info("---------------- After Sorting ---------------------");
			String destinationFilePath = getDestinationFilePath(destinationDir, fileName);
			writeDataToDestFile(pizzaOrderList, destinationFilePath);
		}
	}
	
	private void sortPizzaOrderByName(List<PizzaOrder> pizzaOrderList) {
		Collections.sort(pizzaOrderList, new Comparator<PizzaOrder>() {
			@Override
			public int compare(PizzaOrder o1, PizzaOrder o2) {
				return o1.getOrderName().compareToIgnoreCase(o2.getOrderName());
			}
		});
	}
	
	private void writeDataToDestFile(List<PizzaOrder> pizzaOrders, String destFile) {
		if (!CollectionUtils.isEmpty(pizzaOrders)) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(destFile))) {
				writer.write("Order          time");
				writer.newLine();
			pizzaOrders.forEach(pizzaOrder -> {
				try {
					logger.info(pizzaOrder.getOrderName() + "  "+ pizzaOrder.getTime());
					writer.write(pizzaOrder.getOrderName() + "          "+ pizzaOrder.getTime());
					writer.newLine();
				} catch (IOException e) {
					logger.error("Issue in writing data into destination file, {}", e.getMessage());
					e.printStackTrace();
				}
			});
			} catch (IOException e) {
				logger.error("Issue in writing data into destination file, {}", e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private static String getFileSeparator() {
		return FileSystems.getDefault().getSeparator();
	}
	
	private String getDestinationFilePath(File destinationDir, String fileName) {
		return destinationDir.getAbsolutePath() + getFileSeparator() + fileName;
	}
}