package SeleniumTestNG_F_De.BaseAbstract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumTestNG_F_De.PageObject.landingPage;

public class baseTest {

	public WebDriver driver;
	public landingPage lpO;

	public WebDriver initalizer() throws IOException {

		Properties prop = new Properties();
		// FileInputStream fis = new
		// FileInputStream(System.getProperty("user.dir")+"/src/main/java/SeleniumTestNG_F_De/resources/GlobalData.properties");)
		String filePath = System.getProperty("user.dir")
				+ "/src/main/java/SeleniumTestNG_F_De/resources/GlobalData.properties";
		FileInputStream fis = new FileInputStream(filePath);

		prop.load(fis);

		// to get browser from maven during runtime
		//comand - mvn test -PRegression -Dbrowser=edge
		// C:\Users\Pushkar Singh\eclipse-workspace\18_SeleniumTestNG_F_De> 
		String BrowserName = System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
				//prop.getProperty("browser");

		if (BrowserName.contains("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			
			if(BrowserName.contains("headless")) {
				opt.addArguments("headless");
			}
			driver = new ChromeDriver(opt);
			driver.manage().window().setSize(new Dimension(1440,900));// helps in running full screen in headless mode
		}

		if (BrowserName.equalsIgnoreCase("edge")) {
			// driver = new firefox();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public landingPage submitOrder() throws IOException {
		driver = initalizer();
		lpO = new landingPage(driver);
		lpO.goToPage();
		return lpO;
	}

	@AfterMethod(alwaysRun = true)
	public void closeProgram() {
		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath),
				StandardCharsets.UTF_8);

		// C:\Users\Pushkar
		// Singh\eclipse-workspace\18_SeleniumTestNG_F_De\src\test\java\SeleniumTestNG_F_De\JsonData\purchaseData.json
		// String to HashMap- Jackson Datbind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

		// {map, map}

	}

	public String getScreenShot(String TCName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//Reports//"+TCName+".png");
		FileUtils.copyFile(src, dest);
		
		return System.getProperty("user.dir")+"//Reports//"+TCName+".png";

	}

}
