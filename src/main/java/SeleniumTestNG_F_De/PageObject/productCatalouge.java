package SeleniumTestNG_F_De.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;

public class productCatalouge extends abstractClass {

	WebDriver driver;

	public productCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinnerW;



	By productBy = By.cssSelector(".mb-3");
	//By addToCart = By.xpath("//*[contains(text(),' Add To Cart')]");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessageBy = By.cssSelector("#toast-container");

	public List<WebElement> getProdcutsList() {
		waitForVisiEleLoc(productBy);
		return products;
	}

	public WebElement getProductNameM(String pNameB) {
		WebElement prodCoat = getProdcutsList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(pNameB)).findFirst()
				.orElse(null);
		System.out.println(prodCoat.getText());
		return prodCoat;
	}

	public void addToCartPM(String pNameB) throws InterruptedException {
		getProductNameM(pNameB).findElement(addToCart).click();
		waitForVisiEleLoc(toastMessageBy);
		Thread.sleep(1000);
		//waitForInvisibiltyOf(spinnerW);
	}



}
