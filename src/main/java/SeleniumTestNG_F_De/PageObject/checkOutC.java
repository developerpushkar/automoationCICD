package SeleniumTestNG_F_De.PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;

public class checkOutC extends abstractClass {

	WebDriver driver;

	public checkOutC(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountryW;

	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement selectIndiaW;

	@FindBy(css = ".action__submit")
	WebElement placeOrderW;

	public confirmationPageC placeOrder(String selectCountry) {
		selectCountryW.sendKeys(selectCountry);
		selectIndiaW.click();
		attempt3Click(placeOrderW);
		confirmationPageC cpo = new confirmationPageC(driver);
		return cpo;
	}

}
