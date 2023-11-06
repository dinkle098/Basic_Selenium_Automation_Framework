package Login;

import org.testng.annotations.Test;

import pageObjects.LoginPageObjects;
import reusableComponents.ExcelOperations;
import testBase.TestBase;

public class LoginTest extends TestBase{
	
	@Test
	public void Test() throws Exception
	{
		loginpage = new LoginPageObjects();
		loginpage.LoginAction(uName, pWord);
	}

}
