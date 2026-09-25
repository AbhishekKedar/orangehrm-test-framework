package com.orangehrm.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static String[][] getExcelData(String filePath, String sheetName) {

		try (FileInputStream file = new FileInputStream(filePath);

				Workbook workbook = WorkbookFactory.create(file)) {

			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {

				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			int rowCount = sheet.getLastRowNum();

			int columnCount = sheet.getRow(0).getLastCellNum();

			String[][] data = new String[rowCount][columnCount];

			DataFormatter formatter = new DataFormatter();

			for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {

				Row row = sheet.getRow(rowIndex);

				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

					Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

					data[rowIndex - 1][columnIndex] = formatter.formatCellValue(cell);
				}
			}

			return data;

		} catch (IOException e) {

			throw new RuntimeException("Failed to read Excel file: " + filePath, e);
		}
	}
}