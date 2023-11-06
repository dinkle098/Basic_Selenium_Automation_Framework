package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class LoginPageObjects extends TestBase{
	// HomePage Objects and respective methods 
	
	@FindBy(xpath="//input[@id='txtUserName']")
	WebElement login_username;
	
	@FindBy(id="btnValidateUsername")
	WebElement nextlogin_button;
	
	@FindBy(id="txtPassword")
	WebElement login_password;
	
	@FindBy(id="btnLogin")
	WebElement login_button;
	
	@FindBy(xpath="//span[@id='txtUserName-error']")
	WebElement errormsg_username;
	
	//constructor - to get the initElement method 
	public LoginPageObjects() {
		PageFactory.initElements(driver,this);
	}
	
	public void LoginAction(String uName, String pWord) throws InterruptedException {
		
		login_username.sendKeys(uName);
		Thread.sleep(2000);
		nextlogin_button.click();
		Thread.sleep(2000);
		login_password.sendKeys(pWord);
		Thread.sleep(2000);
		login_button.click();
	}
	 
	public String verifyErrorMessage_Login() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnValidateUsername")));
		Thread.sleep(1500);
		nextlogin_button.click();
		Thread.sleep(1500);
		return errormsg_username.getText();
	}
	
}
