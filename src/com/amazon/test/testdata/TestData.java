package com.amazon.test.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestData {
	
	
	public  static void  writeResultsl(int rowNo,int cellNo,String result) throws IOException{
		
	
		File file = new File("./\\source\\Source.xls");
		FileInputStream fis = new  FileInputStream(file);
		
		HSSFWorkbook   excel = new  HSSFWorkbook(fis); // excel controllable
		HSSFSheet  sheet = excel.getSheet("Scripts");
		HSSFCell cell = sheet.getRow(rowNo).getCell(cellNo);
		cell.setCellValue(result);
		
		
		
		
		System.out.println("writing to Excel the Results: "+result);
		
	FileOutputStream out=	new FileOutputStream(file);
	excel.write(out);
	out.close();
	
	
		
	
		
		
		
	}
	
	public  static String  readExcel(int rowNo, int cellNo) throws IOException{
		String data = null;
		// read
		
		//File
		
		//FileInputStream
		
		//Excel
		
		//Sheet
		
		//Cell
		
		//Data
		
		File file = new File("./\\source\\Source.xls");
		FileInputStream fis = new  FileInputStream(file);
		
		HSSFWorkbook   excel = new  HSSFWorkbook(fis); // excel controllable
		HSSFSheet  sheet = excel.getSheet("Scripts");
		HSSFCell cell = sheet.getRow(rowNo).getCell(cellNo);
		data = cell.getStringCellValue();
		
		
		
		
	//	System.out.println("data from excel: "+data);
		
		return data;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	//write : task
	
	//File
	
	//FileOutputStream
	
	//Excel
	
	//Sheet
	
	//Cell
	
	//Data
	
	
	

}
