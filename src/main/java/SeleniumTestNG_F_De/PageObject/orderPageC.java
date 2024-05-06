package SeleniumTestNG_F_De.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumTestNG_F_De.AbstractReusable.abstractClass;

public class orderPageC extends abstractClass {

	WebDriver driver;

	public orderPageC(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> orderPodW;


	public List<WebElement> ordertProducts() {
		return orderPodW;
	}

	public boolean orderProductMatchFunM(String prodS) {
		boolean match = ordertProducts().stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(prodS));
		return match;

	}



}
