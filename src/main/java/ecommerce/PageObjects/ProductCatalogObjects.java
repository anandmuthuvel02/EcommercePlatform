package ecommerce.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponents;

public class ProductCatalogObjects extends AbstractComponents{
	WebDriver driver;
	public ProductCatalogObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> ProductName = driver.findElements(By.cssSelector(".card-body"));
	@FindBy(css=".card-body")
	List<WebElement> ProductsName;
	
	@FindBy(css="#toast-container")
	WebElement toastContainer;
	
    
	By produtBY = By.cssSelector(".card-body");
	By AddToCart = By.cssSelector(".card-body button:last-of-type");
	public List<WebElement> getProducts() {
		waitUntilElementVisible(produtBY);
		return ProductsName;
	}
	
	public WebElement getProdName(String ExpProduct) {
		WebElement Prd = getProducts().stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(ExpProduct))
				.findFirst().orElseThrow(null);
	    return Prd;
	}
	
	public void addToCartExpectedProduct(String ExpProduct) {
		getProdName(ExpProduct).findElement(AddToCart).click();
	waitUntilElementInvisible(toastContainer);	
		
	}

	
}
