package SeleniumTestNG_F_De.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;

public class landingPage extends abstractClass {

	WebDriver driver;
	public landingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmailE;
	
	@FindBy(id="userPassword")
	WebElement userPasswordE;
	
	@FindBy(id="login")
	WebElement loginE;
	
	//<div class="ng-tns-c4-20 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error" toast-component="" 
	//style="opacity: 1;"><!----><!----><!----><div role="alert" class="ng-tns-c4-20 toast-message ng-star-inserted"
	//aria-label="Incorrect email or password." style=""> Incorrect email or password. </div><!----><!----></div>
	@FindBy(css="div[class*='flyInOut']")
	WebElement errorMessageB;
	
	public productCatalouge loggingIn(String userEmail, String Password) {
		userEmailE.sendKeys(userEmail);
		userPasswordE.sendKeys(Password);
		loginE.click();
		productCatalouge pco = new productCatalouge(driver);
		return pco;
	}
	
	public String errorMessageM() {
		waitForVisibilxtyOf(errorMessageB);
		return errorMessageB.getText();
		
	}
	
	public void goToPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
