package com.hotwire.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.hotwire.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + prop.getProperty("TESTDATA_SHEET_PATH");
	
	static Workbook book;
	static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	public static String getNextDayDepartingDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//Getting current date
		Calendar cal = Calendar.getInstance();
		   
		//Number of Days to add
	    cal.add(Calendar.DAY_OF_MONTH, 1);  
		//Date after adding the days to the current date
		String departingDate = sdf.format(cal.getTime());  
		//Displaying the new Date after addition of Days to current date
		System.out.println("Date after Addition: "+departingDate);
		
		return departingDate;
		
	}
	
	public static String getReturningDate(String departingDate, int daysToAdd ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//Getting current date
		Calendar cal = Calendar.getInstance();
		
		try {
			//Setting the date to the given date
			cal.setTime(sdf.parse(departingDate));
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		//Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
		String returnDate = sdf.format(cal.getTime());
		System.out.println("Date after Addition: "+returnDate);
		
		return returnDate;
		
	}

}
