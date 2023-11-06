package reusableComponents;

import java.io.File;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelOperations {

	String filePath;
	Sheet s1;

	  public ExcelOperations(String sheetName) { 
		try { 
		  filePath = System.getProperty("user.dir")+PropertiesOperations.getPropertyValueByKey("testDataLocation"); 
		} catch (Exception e) { 
		  e.printStackTrace(); 
		}
	  
		//open file - workbook 
		File testDataFile = new File(filePath); 
		Workbook wb = null; 
		try { 
	  		wb = WorkbookFactory.create(testDataFile); 
	  	} catch (Exception e){ 
	  		e.printStackTrace(); 
	  	} 	
	  	s1 = wb.getSheet(sheetName); 
	}

	// get test data from test data sheet in hashmap based on row number
	public HashMap<String, String> getTestDataInMap(int rowNo) throws Exception {

		// read data row by row and put it in Hashmap
		HashMap<String, String> hm = new HashMap<String, String>();

		for (int i = 0; i < s1.getRow(0).getLastCellNum(); i++) {
			s1.getRow(rowNo).getCell(i).setCellType(CellType.STRING);
			hm.put(s1.getRow(0).getCell(i).toString(), s1.getRow(rowNo).getCell(i).toString());

		}
		//System.out.println("HashMAP---" +hm);
		return hm;
	}

	//get row count
	public int getRowCount() {
		return s1.getLastRowNum();
	}

	//get column count
	public int getColumnCount() {
		return s1.getRow(0).getLastCellNum();
	}
}
