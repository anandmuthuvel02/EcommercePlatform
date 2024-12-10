package ecommerce.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerce.AbstractComponents.AbstractComponents;

public class MyCartPageObjects extends AbstractComponents{
	WebDriver driver;
	public MyCartPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> ProductName = driver.findElements(By.cssSelector(".card-body"));
	@FindBy(xpath="//div[@class=\"cartSection\"]/h3")
	List<WebElement> CartProducts;
	
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement CheckOut;
	
	public List<WebElement> getCartProducts() {
		return CartProducts;
	}
	
	public Boolean VerifyExpectedProductSelected(List<WebElement> CartProducts, String ExpProduct ) {
		Boolean Match = CartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(ExpProduct));
	    return Match;
	}
	
	public PaymentPageObjects clickCheckout() {
		CheckOut.click();
		PaymentPageObjects PaymentPage = new PaymentPageObjects(driver);
		return PaymentPage;
	}
}
