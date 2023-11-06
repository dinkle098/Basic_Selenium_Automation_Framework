package testBase;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import reusableComponents.ListenersImplementation;
import reusableComponents.PropertiesOperations;

public class TestBase extends ObjectsRepo{
	
	public String url,uName,pWord,browser;
	
	public void LaunchBrowserAndNavigate() throws Exception {
		//Read the Properties file and get the browser and URL 
		browser = PropertiesOperations.getPropertyValueByKey("browser"); 
		url = PropertiesOperations.getPropertyValueByKey("url");
		uName = PropertiesOperations.getPropertyValueByKey("username");
		pWord = PropertiesOperations.getPropertyValueByKey("password");
		
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("start-maximized");
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
		if(browser.equalsIgnoreCase("Chrome")) {
 			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(url);	
	}
	
	@BeforeMethod
	public void setupMethod() throws Exception {
		
		LaunchBrowserAndNavigate();	
		li.onStart();
	}
	
	@AfterMethod
	public void cleanup() {
		
	//	li.onFinish();
	//	driver.quit();
	}	
}
