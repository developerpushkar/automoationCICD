package SSOL_StepDefination.stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import SeleniumTestNG_F_De.BaseAbstract.baseTest;
import SeleniumTestNG_F_De.PageObject.cartProductPageC;
import SeleniumTestNG_F_De.PageObject.checkOutC;
import SeleniumTestNG_F_De.PageObject.confirmationPageC;
import SeleniumTestNG_F_De.PageObject.landingPage;
import SeleniumTestNG_F_De.PageObject.productCatalouge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImp extends baseTest {
	
	public landingPage lpO;
	public productCatalouge pco;
	public cartProductPageC cpco;
	public checkOutC ckoo;
	public confirmationPageC cpo;
	
	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException{
		lpO = submitOrder();
	}
    
	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_name_and_pass(String username, String Password) {
		 pco = lpO.loggingIn(username, Password);
	}
	
    @When("^I add (.+) to cart$")
    public void i_add_product_to_cart(String product) throws InterruptedException {
    	pco.addToCartPM(product);
		 cpco = pco.addtoCart3Click();
		 
    }
    
    @When("^checkout (.+) and submit the order$")
    public void check_and_submit_order(String product) {
    	Assert.assertTrue(cpco.cartProductMatchFunM(product));
    	 ckoo = cpco.checkOutClickM();
		 cpo = ckoo.placeOrder("india");
		
    }
    
    @Then("^\"([^\"]*)\" message is displayed on confirmation page$")
    public void confirmation_message(String stringMe) {
    	String expectedText = cpo.getExpectedText();
		Assert.assertTrue(expectedText.equalsIgnoreCase(stringMe));
		driver.close();
    }

//    @Then("{string} message is displayed on ConfirmationPage")
//    public void message_displayed_confirmationPage(String string)
//    {
//    	String confirmMessage = confirmationPage.getConfirmationMessage();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
//		driver.close();
//    }
    
}
