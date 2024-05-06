package SeleniumTestNG_F_De.AbstractReusable;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTestNG_F_De.PageObject.cartProductPageC;
import SeleniumTestNG_F_De.PageObject.orderPageC;

public class abstractClass {

	WebDriver driver;

	public abstractClass(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement dasCartCliW;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement dasMyOrderW;
	
	public cartProductPageC addtoCart3Click() {
		attempt3Click(dasCartCliW);
		cartProductPageC cpco = new cartProductPageC(driver);
		return cpco;
	}
	public orderPageC order3Click() {
		attempt3Click(dasMyOrderW);
		orderPageC opO = new orderPageC(driver);
		return opO;
	}


	public void waitForVisiEleLoc(By locatorB) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorB));
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locatorB));
	}
	public void waitForInvisibiltyOf(WebElement  ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForVisibilxtyOf(WebElement  ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void attempt3Click(WebElement ele) {
		int retryCountCart = 0;
		boolean cartClicked = false;
		while (retryCountCart < 3 && !cartClicked) {
			try {
				// Locate the element and attempt to click
				ele.click();
				cartClicked = true; // Set flag to true if click succeeds
			} catch (ElementClickInterceptedException e) {
				// If ElementClickInterceptedException occurs, retry clicking
				retryCountCart++;
				System.out.println("Click failed, retrying... Attempt " + retryCountCart);
			}
		}
	}

}
