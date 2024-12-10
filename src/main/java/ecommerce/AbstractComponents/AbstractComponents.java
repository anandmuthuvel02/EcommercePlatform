package ecommerce.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.PageObjects.MyCartPageObjects;
import ecommerce.PageObjects.MyOrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	

	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Cartbtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrdersBtn;
	

	
	public void waitUntilElementInvisible(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(elem));
	}
	
	public void waitUntilElementVisible(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitUntilWebElementVisible(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1085,2500)");
	}
	public MyCartPageObjects goToCartPage() {
		Cartbtn.click();
		MyCartPageObjects CartPage = new MyCartPageObjects(driver);
		return CartPage;
	}
	public MyOrdersPage goToOrdersPage() {
		OrdersBtn.click();
		MyOrdersPage OrdersPage = new MyOrdersPage(driver);
		return OrdersPage;
	}
	
}
