package Login;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pageObjects.LoginPageObjects;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class ErrorTests extends TestBase{
	
	@Test
	public void errorMessage_Login() throws InterruptedException {
		//open browser and navigate to URL --> Handled in beforeMethod
		//click on next button 
		loginpage = new LoginPageObjects();
		test.log(Status.PASS, "Error message: Please enter your username.");
		String actualErrorMessage = loginpage.verifyErrorMessage_Login();
		String expectedErrorMessage = "Please enter your username.";
		//verify the error message
		assertEquals(actualErrorMessage, expectedErrorMessage);
		
	}
}