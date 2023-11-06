package pageObjects;


import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.invokers.TestMethodWithDataProviderMethodWorker;

import reusableComponents.CommonMethods;
import testBase.TestBase;

public class HomePageObjects extends TestBase{
	
	@FindBy(xpath=".//div[@id='workspaceModeOnOff']/div[1]/div[2]/div[2]/div/span/span[1]/span[2]") 
	public WebElement dropdown; 
	
	@FindBy(xpath=".//ul[@id='ulTabs']//li")
	public WebElement homePageTabs;
	
	@FindBy(xpath=".//ul[@id='drdWSSort_listbox']//li")
	public WebElement homePageDD;
	
	@FindBy(xpath="//span[@id='spanOrganizationCount']")
	public WebElement orgCount;
	
	@FindBy(xpath="//div[@id='pre_loader_wrap']/div[1]")
	public WebElement homePageLoader;
	
	@FindBy(xpath="//div[contains(@class,'main-content')]/div/div[@class='k-loading-image']")
	public WebElement documentPageLoader;
	
	public HomePageObjects() {
		PageFactory.initElements(driver,this);
		cm = new CommonMethods();
	}
	
	public void getHomePageTabs(HashMap<String, String> testData) {
		List<WebElement> homePageTabsValues = driver.findElements(By.xpath(".//ul[@id='ulTabs']/li/a"));
		cm.getTabValues(homePageTabsValues, testData);
	}
	
	public void clickOnOrg(HashMap<String, String> testData) {
		List<WebElement> homePageOrg = driver.findElements(By.xpath("//div[contains(@class,'organization-name')]/span[1]/a"));
		for(int i=0;i<homePageOrg.size();i++)
		{
			if(homePageOrg.get(i).getText().equalsIgnoreCase(testData.get("Organization Name")))
			{
				System.out.println("Organization Name is: "+homePageOrg.get(i).getText());
				driver.findElement(By.xpath("//div[@id='listOrganizationGroup']/div/div["+(i+1)+"]/div/div[1]")).click();
				break;
			}
		}
	}
	
	public void clickOnWS(HashMap<String, String> testData) throws InterruptedException {
		List<WebElement> homePageWS = driver.findElements(By.xpath("//div[@id='listViewGroup']/div/div"));
		for (int i=0; i<homePageWS.size(); i++) {
			if(homePageWS.get(i).getText().startsWith(testData.get("Workspace Name")))
			{
				System.out.println("Workspace Name is: "+homePageWS.get(i).getText());
				driver.findElement(By.xpath("//div[@id='listViewGroup']//div["+(i+1)+"]//div[contains(@class,'case-name')]/a")).click();
				
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait1.until(ExpectedConditions.invisibilityOf(documentPageLoader));
				wait1.until(ExpectedConditions.invisibilityOf(homePageLoader));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				break;
			}
		}
	}
	
	public List<String> getDropDownValues() throws InterruptedException {
		dropdown.click();
		Thread.sleep(2000);
		List<WebElement> DDL=driver.findElements(By.xpath(".//div[contains(@style,'display: block')]//ul[@id='drdWSSort_listbox']//li"));
		return cm.getDropDownOptionsAsList(DDL);
	}
	
	
}
