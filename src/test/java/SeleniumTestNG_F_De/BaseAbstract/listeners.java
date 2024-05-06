package SeleniumTestNG_F_De.BaseAbstract;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumTestNG_F_De.resources.extentReportC;

public class listeners extends baseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = extentReportC.extentReportM();
	ThreadLocal<ExtentTest> tLocalO = new ThreadLocal<ExtentTest>();

	
	 @Override
	    public void onTestStart(ITestResult result) {
	       // System.out.println("Test Started: " + result.getName());
	       test = extent.createTest(result.getMethod().getMethodName());
	       tLocalO.set(test); // to creat unique ids for each "test" , so that in parallel run it wont confuse
	       
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        //System.out.println("Test Passed: " + result.getName());
	    	tLocalO.get().log(Status.INFO, "Test Is passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        //System.out.println("Test Failed: " + result.getName());
	    	try {
				driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	String filePath = null;
			try {
				filePath = getScreenShot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tLocalO.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
			tLocalO.get().fail(result.getThrowable());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        //System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Not implemented
	    }

	    @Override
	    public void onFinish(ITestContext result ) {
	        System.out.println("Test Suite Finished: " + result.getName());
	        extent.flush();
	    }
	   
}
