package HomePage;
			
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;	
import com.aventstack.extentreports.Status;	
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import reusableComponents.ExcelOperations;
import testBase.TestBase;
	
	public class HomePageTest extends TestBase {
		
		ExcelOperations e = new ExcelOperations("CasepointARA");
	
		@Test
		public void verifyHomePageDDOptions(Object obj1) throws InterruptedException {
			
			HashMap<String, String> testData = (HashMap<String, String>) obj1;

			loginpage = new LoginPageObjects();
			homepage = new HomePageObjects();
	
			li.onTestStart("qwerty");
	
			loginpage.LoginAction(uName, pWord);
		
			Thread.sleep(7000);
		
			driver.switchTo().frame("frmPageLoad");
			
			Thread.sleep(2000);
			homepage.getHomePageTabs(testData);
			Thread.sleep(2000);
			homepage.clickOnOrg(testData);
			Thread.sleep(5000);
			  
			List<String> actualValues = homepage.getDropDownValues();
			test.log(Status.INFO,"Actual values of drop down on Home Page: "+actualValues);
			System.out.println("Actual Values: "+actualValues); 
			List<String> expectedValues = Arrays.asList("Recent","Ascending","Descending","Created Date");
			test.log(Status.INFO,"Expected values of drop down on Home Page: "+expectedValues);
			System.out.println("Expected Values: "+expectedValues);
			Assert.assertEquals(actualValues,expectedValues,"Error in Dropdown options comparision");
				 
			homepage.clickOnWS(testData);
			
			//driver.switchTo().defaultContent();
			}
		
		// DataProvider method -> returns object array 
		@DataProvider (name = "HomePage")
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
