package com.scania.scaniaDevUIFramewok.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/TestData/Employees.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestdata(String sheetName) {
		Object[][] data = null;
		try {
			FileInputStream file = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);

			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();
			data = new Object[totalRows][totalColumns];

			for (int i = 0; i < totalRows; i++) {
				for (int j = 0; j < totalColumns; j++) {
					Cell cell = sheet.getRow(i + 1).getCell(j);
					
					try {
						if (j < totalColumns - 2) {
							data[i][j] = cell.getStringCellValue();
						} else {
							data[i][j] = ""+ new BigDecimal(cell.getNumericCellValue());
						}
						System.err.println(data[i][j]);
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}
}
