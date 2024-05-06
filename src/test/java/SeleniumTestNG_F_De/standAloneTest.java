package SeleniumTestNG_F_De;

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

import SeleniumTestNG_F_De.PageObject.landingPage;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String prodS = "ZARA COAT 3";
		boolean cartClicked = false;
		int retryCountCart = 0;
		boolean clicked = false;
		int retryCount = 0;

		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		landingPage lpO = new landingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("kishan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kishansingh1");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// Thread.sleep(5000);

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prodCoat = products.stream()
				.filter(s -> s.findElement(By.tagName("b")).getText().equalsIgnoreCase(prodS)).findFirst().orElse(null);

		prodCoat.findElement(By.xpath("//*[contains(text(),' Add To Cart')]")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		while (retryCountCart < 3 && !cartClicked) {
			try {
				// Locate the element and attempt to click
				driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
				cartClicked = true; // Set flag to true if click succeeds
			} catch (ElementClickInterceptedException e) {
				// If ElementClickInterceptedException occurs, retry clicking
				retryCountCart++;
				System.out.println("Click failed, retrying... Attempt " + retryCountCart);
			}
		}

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(prodS));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();

		while (retryCount < 3 && !clicked) {
			try {
				// Locate the element and attempt to click
				WebElement element = wait
						.until(ExpectedConditions.elementToBeClickable((By.cssSelector(".action__submit"))));
				element.click();
				clicked = true; // Set flag to true if click succeeds
			} catch (ElementClickInterceptedException e) {
				// If ElementClickInterceptedException occurs, retry clicking
				retryCount++;
				System.out.println("Click failed, retrying... Attempt " + retryCount);
			}
		}

		String expectedText = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(expectedText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();

	}

}
