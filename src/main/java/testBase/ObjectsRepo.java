package testBase;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pageObjects.DocumentPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import reusableComponents.CommonMethods;
import reusableComponents.ListenersImplementation;

public class ObjectsRepo {
	public static WebDriver driver; 
	public static ExtentTest test;
	public static ExtentReports extent;
	
	public LoginPageObjects loginpage ;
	public HomePageObjects homepage ;
	public CommonMethods cm ;
	public DocumentPageObjects documentpage;
	
	public static ListenersImplementation li = new ListenersImplementation();
	

}
