package ecommerce.test;

import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class EcommerceTest {

	public static void main(String[] args) {

		String Product = "ADIDAS ORIGINAL";
		String CountryN = "Ind";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// LoginPageObjects login = new LoginPageObjects(driver);

		driver.findElement(By.id("userEmail")).sendKeys("anandmuthuvel02@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Asdf@2024");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> ProductName = driver.findElements(By.cssSelector(".card-body"));

		/*
		 * Using Loops for(int i=0; i<products.size();i++) {
		 * System.out.println(ProductName.get(i).getText());
		 * if(ProductName.get(i).getText().equalsIgnoreCase("ADIDAS ORIGINAL")) {
		 * products.get(i).click(); break; } }
		 */

		// Using Streams
		WebElement Prd = ProductName.stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(Product))
				.findFirst().orElseThrow(null);

		Prd.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector(".btn.btn-custom[routerlink*='cart']")).click();

		List<WebElement> Prods = driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));

		Boolean Match = Prods.stream().anyMatch(s -> s.getText().equalsIgnoreCase(Product));
		Assert.assertTrue(Match);

		driver.findElement(By.xpath("//*[text()='Checkout']")).click();

		driver.findElement(By.xpath("//input[@class='input txt text-validated' and @placeholder = 'Select Country']"))
				.sendKeys(CountryN);

		List<WebElement> Contries = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		WebElement Country = Contries.stream().filter(s -> s.getText().startsWith(CountryN)).findFirst()
				.orElseThrow(null);
		Country.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1085,2500)");

		
		  wait.until(ExpectedConditions .visibilityOf(driver.findElement(By.
		  xpath("//a[normalize-space()='Place Order']"))));
		 
		//driver.findElement(By.cssSelector(".actions a")).click();
		 driver.findElement(with(By.tagName("a")).below(By.cssSelector(".user__address"))).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText()
				.equalsIgnoreCase("Thankyou for the order."));
	}

}
