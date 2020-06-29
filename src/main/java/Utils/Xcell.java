package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xcell {    
	
	public static File file;

	public static List<String> getTestData(String sheetName) throws Exception {
	   File file = new File("src/test/resources/Test.xlsx");
	   FileInputStream input= new FileInputStream(file);
	   XSSFWorkbook workbook = new XSSFWorkbook(input);
	   XSSFSheet Sheet = workbook.getSheet(sheetName);
	   XSSFRow row = Sheet.getRow(0);	    
	   List<String> data = new ArrayList<String>();	    
	   for(Iterator<Cell> ri = row.cellIterator(); ri.hasNext();) {
		   data.add(String.valueOf(ri.next()));
	   }   
	   workbook.close();	   
	   return data;
	}
	
	public static Object[][] getTestData2d(String sheetName) throws Exception {
		   File file = new File("src/test/resources/Test.xlsx");
		   FileInputStream input= new FileInputStream(file);
		   XSSFWorkbook workbook = new XSSFWorkbook(input);
		   XSSFSheet Sheet = workbook.getSheet(sheetName);
		   XSSFRow row = Sheet.getRow(0);
		   int noOfColumns= row.getPhysicalNumberOfCells();
		   int noOfRows = Sheet.getPhysicalNumberOfRows();	 

		   Object Data[][] = new Object[noOfRows][noOfColumns];		   
		   for(int i=0;i<noOfRows;i++){
			   row = Sheet.getRow(i);	
			   if(row!=null) {			   
			   	   for(int j=0;j<noOfColumns;j++) {	
			   		   if(row.getCell(j)!=null)Data[i][j]=String.valueOf(row.getCell(j));	   
			   	   }		
			   }	   
		   }	   	   
		   workbook.close();		   
		   return Data;
		}
	public static void createResultxcell() throws Exception {
		file = new File("Result.xlsx");
		if(!file.exists()) {
			XSSFWorkbook workBook = new XSSFWorkbook();
			String[] testClass = {"CarTest", "HealthTest", "TravelTest"};
			for(String test : testClass) {
				XSSFSheet sheet = workBook.createSheet(test);				
				Row row = sheet.createRow(0);
				Cell cell1 = row.createCell(0);
				Cell cell2 = row.createCell(1);
				Cell cell3 = row.createCell(2);			
				cell1.setCellValue("testName");				
				cell2.setCellValue("testOutput");				
				cell3.setCellValue("testStatus");				
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
			}
			FileOutputStream output = new FileOutputStream(file);
			workBook.write(output);
			workBook.close();
			output.close();
		}
	}	

	
	public static void writeToExcell(String sheetName, String testName, String Msg, String Status) throws Exception {
		createResultxcell();		
		FileInputStream input= new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(input);
		XSSFSheet sheet = workBook.getSheet(sheetName);
		int rownum = sheet.getPhysicalNumberOfRows();		;
		Row row = sheet.createRow(rownum);
		Cell cell1 = row.createCell(0);		
		cell1.setCellValue(testName);
		Cell cell2 = row.createCell(1);		
		cell2.setCellValue(Msg);
		Cell cell3 = row.createCell(2);		
		cell3.setCellValue(Status);
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		FileOutputStream output = new FileOutputStream(file);
		workBook.write(output);
		workBook.close();		
	}

}