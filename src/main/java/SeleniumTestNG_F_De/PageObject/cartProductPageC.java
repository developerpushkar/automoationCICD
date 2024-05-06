package SeleniumTestNG_F_De.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;

public class cartProductPageC extends abstractClass {

	WebDriver driver;

	public cartProductPageC(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProductsW;

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkOutW;

	public List<WebElement> cartProducts() {
		
		return cartProductsW;
	}


	public boolean cartProductMatchFunM(String prodS) {
		
		boolean match = cartProducts().stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(prodS));
		System.out.println("Result of Match Functio is : "+ match);
		return match;

	}
	

	public checkOutC checkOutClickM() {
		checkOutW.click();
		 checkOutC ckoo = new checkOutC(driver);
		 return ckoo;
	}

}
