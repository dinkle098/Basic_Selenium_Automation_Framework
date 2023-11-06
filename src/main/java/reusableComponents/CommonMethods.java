package reusableComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class CommonMethods extends TestBase {
	
	//get drop down options in list of string for compare
	public List<String> getDropDownOptionsAsList(List<WebElement> element) {
		List<String> actualContents = new ArrayList<String>();
		for (WebElement ref : element) {
			actualContents.add(ref.getText());
		}
		return actualContents;
	}
	
	//get all values of tabs from Home Page
	public void getTabValues(List<WebElement> element, HashMap<String, String> testData) {
		System.out.println("------Clicking on All tabs-----");
		for (WebElement ref : element) {		
			if(ref.getText().equalsIgnoreCase(testData.get("HomePageTabs"))){
				System.out.println("Home page tab from excel: "+ref.getText().equalsIgnoreCase(testData.get("HomePageTabs")));
				ref.click();
				break;
			}
		}
	}
	
	//selecting values from drop down
	/*
	 * public void selectValuesFromDropdown(List<WebElement> element, String
	 * dropdownValue) {
	 * 
	 * }
	 */
	
	//selecting values from check boxes
	public void selectCheckbox(List<WebElement> element, String checkboxValue) {
		String[] checkboxArray = checkboxValue.split(",");
		for (String str : checkboxArray) {	//reviewer,client admin
			for(WebElement ele : element) {
				ele.getText().equalsIgnoreCase(str);
				ele.click();
				break;
			}
		}
	}
	
	
}
