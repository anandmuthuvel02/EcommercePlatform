package ecommerce.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ecommerce.AbstractComponents.AbstractComponents;

public class OrderConfirmationPageObjects extends AbstractComponents{
	WebDriver driver;
	public OrderConfirmationPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".hero-primary")
	WebElement Cofirmation;
	
	public String getConfirmMsg() {
	 return Cofirmation.getText();
	}
}
	
	