package com.OnlineBanking.Steller.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of excel related methods like insert data, fetch data, getrownumber
 * @author HP
 *
 */
public class ExcelUtilities {
	
	/**
	 * This method is used to insert data into excel sheet
	 * @param sheet
	 * @param row
	 * @param cell
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void insertDataIntoExcel(String sheet, int row, int cell, String data) throws EncryptedDocumentException, IOException {
		FileInputStream fin = new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet s = wb.getSheet(sheet);
		Row r = s.createRow(row);
		Cell c = r.createCell(cell);
		c.setCellValue(data);
		FileOutputStream fout=new FileOutputStream(IPathConstants.excelFilePath);
		wb.write(fout);
		wb.close();
	}
	
	/**
	 * This method is used to fetch the data from excel
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public String getExcelData(String sheet, int row, int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fin = new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet s = wb.getSheet(sheet);
		Row r = s.getRow(row);
		Cell c = r.getCell(cell);
		DataFormatter formatdata=new DataFormatter();
		String data=formatdata.formatCellValue(c);
	//	String s1 = c.toString();
		
		
		return data;
	}
	
	/**
	 * This method is used to get count of rows created in excel sheet
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowNumber(String sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fin = new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet s = wb.getSheet(sheet);
		int row = s.getLastRowNum();
		return row;
	}
	
	public HashMap<String,String> getMultipleData(String sheetname) throws Throwable {
		
		FileInputStream fis=new FileInputStream(IPathConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		int count=sh.getLastRowNum();
		
		
		HashMap<String, String> hs=new HashMap<String, String>();
		
		for (int i = 0; i <= count; i++) 
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			hs.put(key, value);
		}
		return hs;
		
	}
}

