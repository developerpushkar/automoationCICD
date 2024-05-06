package SeleniumTestNG_F_De;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;
import SeleniumTestNG_F_De.BaseAbstract.baseTest;
import SeleniumTestNG_F_De.PageObject.cartProductPageC;
import SeleniumTestNG_F_De.PageObject.checkOutC;
import SeleniumTestNG_F_De.PageObject.confirmationPageC;
import SeleniumTestNG_F_De.PageObject.landingPage;
import SeleniumTestNG_F_De.PageObject.orderPageC;
import SeleniumTestNG_F_De.PageObject.productCatalouge;

public class loginPageT extends baseTest {

	String prodS = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchaseG" })
	public void testNGTest(HashMap<String, String> input) throws IOException, InterruptedException {
		productCatalouge pco = lpO.loggingIn(input.get("email"), input.get("pass"));

		pco.addToCartPM(input.get("prodS"));
		cartProductPageC cpco = pco.addtoCart3Click();

		Assert.assertTrue(cpco.cartProductMatchFunM(input.get("prodS")));
		checkOutC ckoo = cpco.checkOutClickM();
		confirmationPageC cpo = ckoo.placeOrder("india");
		String expectedText = cpo.getExpectedText();
		Assert.assertTrue(expectedText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider = "getData",dependsOnMethods = { "testNGTest" })
	public void verifyProd(HashMap<String, String> input) {
		productCatalouge pco = lpO.loggingIn(input.get("email"), input.get("pass"));
		orderPageC opO = pco.order3Click();
		// opO.orderProductMatchFunM(prodS);
		Assert.assertTrue(opO.orderProductMatchFunM(input.get("prodS")));
	}

//	@DataProvider
//	public Object[][] getData() 
//	{
//		return new Object[][] {{"kishan@gmail.com","Kishansingh1","ZARA COAT 3"},{"ssol@gmail.com","Kishansingh1","IPHONE 13 PRO"}};
//	}

	// Dataprovider with HashMap
//	@DataProvider
//	public Object[][] getData() 
//	{
//		HashMap<String,String> data1 = new HashMap<String, String>();
//		data1.put("email", "ssol@gmail.com");
//		data1.put("pass", "Kishansingh1");
//		data1.put("prodS", "ZARA COAT 3");
//		
//		HashMap<String,String> data2 = new HashMap<String, String>();
//		data2.put("email", "kishan@gmail.com");
//		data2.put("pass", "Kishansingh1");
//		data2.put("prodS", "IPHONE 13 PRO");
//		
//		return new Object[][] {{data1},{data2}};
//	}

	// Json Data
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				"//src//test//java//SeleniumTestNG_F_De//JsonData//purchaseData.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
