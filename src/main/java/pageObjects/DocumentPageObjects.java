package pageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reusableComponents.CommonMethods;
import testBase.TestBase;

public class DocumentPageObjects extends TestBase{
	
	@FindBy(xpath="//div[@id='divPageMenuEDD']//li/a/span[1]")
	public WebElement leftPaneOptions;
	
	@FindBy(xpath="//a[@id='folderIcon']")
	public WebElement FolderIcon;
	
	@FindBy(xpath="//div[@class='dropdown open']/ul/li")
	public WebElement foldersMenu;
	
	@FindBy(xpath="//div[contains(@class,'left-sidebar-content display')]//div[@class='k-loading-image']")
	public WebElement leftPanelFacetLoader;
	
	@FindBy(xpath="//main[@id='divReviewDocContainer']/div[1]/div[1]")
	public WebElement reviewGridLoader;
	
	@FindBy(xpath="//input[@id='txtFolderName']")
	public WebElement folderName;
	
	@FindBy(xpath="//div[@id='folderPermissiongGroup']//div/input")
	public WebElement folderPermission;
	
	@FindBy(xpath="//ul[@id='ddlFolderColor_listbox']/li")
	public WebElement folderColor;
	
	@FindBy(xpath="//div[@id='workFolderRoleGrid']/div[2]/table/tbody/tr")
	public WebElement assignRole;
	
	@FindBy(xpath="//div[@id='workFolderRoleGrid']//tr[5]/td[1]")
	public WebElement addFolderReviewerRole;
	
	@FindBy(xpath="//div[@id='workFolderRoleGrid']/div[2]//tr/td[1]")
	public WebElement assignRoleCheckbox;
	
	@FindBy(xpath="//button[@id='btnFolderSave']")
	public WebElement saveAndCloseFolder;
	
	public DocumentPageObjects() {
		PageFactory.initElements(driver, this);
		cm = new CommonMethods();
	}
	
	public void getLeftPaneOptions(HashMap<String, String> testData) {
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.invisibilityOf(leftPanelFacetLoader));
		
		List<WebElement> leftPaneValues = driver.findElements(By.xpath("//ul[@id='ulLeftTabStrip']/li/a"));
		
		  for (int i=0; i<leftPaneValues.size();i++) { 
			  if (leftPaneValues.get(i).getText().equalsIgnoreCase(testData.get("LeftPaneOptions"))) { 
				  	leftPaneValues.get(i).click();
				  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
				  	break; 
			  } 
		  } 
	}
	
	public void addParentFolder(HashMap<String, String> testData) {
	
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.visibilityOf(FolderIcon));

		FolderIcon.click();
		
		List<WebElement> foldersMenuOptions = driver.findElements(By.xpath("//div[@class='dropdown open']/ul/li"));
		for (int i=0; i<foldersMenuOptions.size();i++) {
			if (foldersMenuOptions.get(i).getText().startsWith("Add Folders")) {
				foldersMenuOptions.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				break;
			}
		}
		
		folderName.click();
		folderName.sendKeys("F1");
		
		List<WebElement> fpermission = driver.findElements(By.xpath("//div[@id='folderPermissiongGroup']//label[@class='k-radio-label']"));
		
		//Select roles by clicking on check box
		cm.selectCheckbox(fpermission, testData.get("Permission"));
		
		/*
		 * for (int i=0; i<fpermission.size();i++) { if
		 * (fpermission.get(i).getText().startsWith(testData.get("Permission"))) {
		 * assignPermissionToPublicFolder(testData); break; } else {
		 * fpermission.get(i).click(); break; } }
		 */
	}
	
	public void assignPermissionToPublicFolder(HashMap<String, String> testData) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", addFolderReviewerRole);
		
		List<WebElement> listOfRoles = driver.findElements(By.xpath("//div[@id='workFolderRoleGrid']//tr/td[2]"));
		for (int j=0; j<listOfRoles.size();j++) {
			
			System.out.println("role permission in folders:"+listOfRoles.get(j).getText());
			if (listOfRoles.get(j).getText().startsWith(testData.get("Roles"))) {
				driver.findElement(By.xpath("//div[@id='workFolderRoleGrid']/div[2]//tr["+(j+1)+"]/td[1]")).click();
				//assignRoleCheckbox.click(); 
				break;
			}
		}
	}
	
	public void saveAndCloseFolder() {
		saveAndCloseFolder.click();
	}
	
	
}
