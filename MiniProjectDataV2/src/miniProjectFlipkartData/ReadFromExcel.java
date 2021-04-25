package miniProjectFlipkartData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

public class ReadFromExcel {
	public static ArrayList<ArrayList<String>> dataArray = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> readExcel(String filePath, String fileName, String sheetName) throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook dataWorkbook = null;

			dataWorkbook = new XSSFWorkbook(inputStream);
			
		// Read sheet inside the workbook by its name

		Sheet dataSheet = dataWorkbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = dataSheet.getLastRowNum() - dataSheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it
		
		
		for (int i = 0; i < rowCount + 1; i++) {

			Row row = dataSheet.getRow(i);
			
			for (int j = 0; j < row.getLastCellNum(); j++) {
				dataArray.add(new ArrayList <String>()); // create new element in ArrayList
				//System.out.println(row.getCell(j));
				// copy data from excel to ArrayList
				try {
					dataArray.get(i).add(j, row.getCell(j).getStringCellValue());
				} catch (Exception e) {
					double d = row.getCell(j).getNumericCellValue(); // if cell is numeric type
					int value = (int) d;
					dataArray.get(i).add(j, String.valueOf(value)); //convert numeric value to string
				}
			}
		}
		dataWorkbook.close();
		return dataArray; // return the ArrayList

	}
}
