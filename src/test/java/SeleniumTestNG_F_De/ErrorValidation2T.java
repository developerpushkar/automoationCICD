package SeleniumTestNG_F_De;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;
import SeleniumTestNG_F_De.BaseAbstract.baseTest;
import SeleniumTestNG_F_De.BaseAbstract.retryInterfaceC;
import SeleniumTestNG_F_De.PageObject.cartProductPageC;
import SeleniumTestNG_F_De.PageObject.checkOutC;
import SeleniumTestNG_F_De.PageObject.confirmationPageC;
import SeleniumTestNG_F_De.PageObject.landingPage;
import SeleniumTestNG_F_De.PageObject.productCatalouge;

public class ErrorValidation2T extends baseTest {

	@Test(groups= {"errorValidaionG"},retryAnalyzer=retryInterfaceC.class)
	
	public void testNGTest() throws IOException, InterruptedException {

		String prodS = "ZARA COAT 3";

		lpO.loggingIn("idiotNahiHaiHum@gmail.com", "Kinsingh1");
		Assert.assertEquals(lpO.errorMessageM(), "Incorrect email or password.");
	}

	@Test
	public void productErrorValidationM() throws IOException, InterruptedException {

		String prodS = "ZARA COAT 3";

		productCatalouge pco = lpO.loggingIn("ssol@gmail.com", "Kishansingh1");

		// pco.getProdcutsList();
		pco.addToCartPM(prodS);
		cartProductPageC cpco = pco.addtoCart3Click();

		Assert.assertTrue(cpco.cartProductMatchFunM("ZARA COAT 3"));

	}

}
