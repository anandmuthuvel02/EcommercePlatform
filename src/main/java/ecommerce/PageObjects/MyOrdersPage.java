package ecommerce.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponents;

public class MyOrdersPage extends AbstractComponents {
	WebDriver driver;
	public MyOrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderedProducts;
	
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement CheckOut;
	
	
	public Boolean VerifyProductReflectedInOrders(String ExpProduct ) {
		
		Boolean Match = OrderedProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(ExpProduct));
	    return Match;
	}
	
}
