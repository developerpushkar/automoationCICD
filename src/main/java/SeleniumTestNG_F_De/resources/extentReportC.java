package SeleniumTestNG_F_De.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public  class extentReportC {
	
	public static ExtentReports extentReportM() {
		String reportSavingPath = System.getProperty("user.dir") + "//Reports//index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportSavingPath);

		sparkReporter.config().setReportName("SSOL Pushkar Automation Report");
		sparkReporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Tester Name", "Pushkar Singh");
		
		return extent;
	}
	
	
}
