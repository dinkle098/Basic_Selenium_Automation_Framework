package DocumentsPage;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pageObjects.DocumentPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import reusableComponents.ExcelOperations;
import testBase.TestBase;

public class DocumentsPageTest extends TestBase{

	ExcelOperations e = new ExcelOperations("CasepointARA");
	
	@Test (dataProvider = "DocumentsPage")
	public void VerifyLeftPane(Object obj1) throws InterruptedException {
		//convert obj1 to Hashmap
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		li.onTestStart("qwerty");
		test.log(Status.INFO, "Test Data used for execution is: "+testData);
		
		  loginpage = new LoginPageObjects(); 
		  loginpage.LoginAction(uName, pWord);
		  Thread.sleep(7000);
		  
		  driver.switchTo().frame("frmPageLoad"); 
		  Thread.sleep(2000);
		  
		  homepage = new HomePageObjects(); 
		  homepage.getHomePageTabs(testData);
		  Thread.sleep(2000); 
		  homepage.clickOnOrg(testData); 
		  Thread.sleep(2000);
		  homepage.clickOnWS(testData);
		  
		  documentpage = new DocumentPageObjects(); 
		  driver.switchTo().defaultContent();
		  driver.switchTo().frame("frmPageLoad");
		  documentpage.getLeftPaneOptions(testData);
		  documentpage.addParentFolder(testData); 
		  documentpage.saveAndCloseFolder();
		 
	}
	
	// DataProvider method -> returns object array 
	@DataProvider (name = "DocumentsPage")
	public Object[][] getTestDataFromSheet() throws Exception {
		Object[][] obj = new Object[e.getRowCount()][1];
		for (int i = 0; i < e.getRowCount(); i++) {
			
			HashMap<String, String> testData = e.getTestDataInMap(i+1);
			
			// putting the data from Hashmap to object array since the data provider is returning the object array. 
			obj[i][0] = testData;
		}
		return obj;
	}
}
