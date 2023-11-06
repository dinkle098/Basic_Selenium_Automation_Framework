package reusableComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.ObjectsRepo;
import testBase.TestBase;

public class ListenersImplementation extends TestBase implements ITestListener{

	public void onTestStart(String result) {
		//before test case it will be executed
		test = extent.createTest(result);
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+" is PASSED.");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+" is FAILED");
		test.log(Status.FAIL, result.getThrowable());
		
		// To capture and attach the screenshot for failed test
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		
		//format method will return date in specified date format
		String actualDate = format.format(date);
		
		String screenshotPath = System.getProperty("user.dir")+"/Reports/Screenshots/ExecutionReport_"+actualDate+".jpeg";
		
		File dest = new File(screenshotPath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(screenshotPath, "Failed Test Screenshot");
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart() {
		//setup method call
		extent = ExtendSetup.setupExtentReport();
	}

	public void onFinish() {
		//close extent method
		extent.flush();
	}

}
