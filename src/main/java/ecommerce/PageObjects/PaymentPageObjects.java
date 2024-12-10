package ecommerce.PageObjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

import ecommerce.AbstractComponents.AbstractComponents;

public class PaymentPageObjects extends AbstractComponents{
	WebDriver driver;
	public PaymentPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> ProductName = driver.findElements(By.cssSelector(".card-body"));
	@FindBy(xpath="//input[@class='input txt text-validated' and @placeholder = 'Select Country']")
	WebElement Countrytxt;
	
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	List<WebElement> CountriesName;
	
	@FindBy(css=".action__submit")
	WebElement PlaceOrderBtn;
	
	By tag = By.tagName("a");
	
	@FindBy(css=".user__address")
	WebElement relativePlaceOrdertag;
	
	
	
	By PlcaeOrderBy = By.cssSelector(".btnn.action__submit.ng-star-inserted"); 
	public OrderConfirmationPageObjects selectCountry(String CountryName) {
		Countrytxt.sendKeys(CountryName);
		WebElement Country = CountriesName.stream().filter(s -> s.getText().startsWith(CountryName)).findFirst()
				.orElseThrow(null);
		Country.click();
		scrollDown();
		waitUntilElementVisible(PlcaeOrderBy);
		//driver.findElement(with(tag).below(relativePlaceOrdertag)).click();
		PlaceOrderBtn.click();
		OrderConfirmationPageObjects  OrderConfirmation =new OrderConfirmationPageObjects(driver);
		return OrderConfirmation;
	}
}
