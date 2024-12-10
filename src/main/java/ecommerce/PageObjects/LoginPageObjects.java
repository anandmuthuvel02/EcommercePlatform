package ecommerce.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponents;

public class LoginPageObjects extends AbstractComponents {
	
	WebDriver driver;
	public LoginPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement Email = driver.findElement(By.id("userEmail"));
	//Page Factory 
	@FindBy(id="userEmail")
	WebElement Emailtxt;
	
	//WebElement Password = driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement Passwordtxt;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement Loginbtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	
	
	public ProductCatalogObjects loginApplication(String email,String password) {
		Emailtxt.sendKeys(email);
		Passwordtxt.sendKeys(password);
		Loginbtn.click();
		ProductCatalogObjects ProductCatalog = new ProductCatalogObjects(driver);
		return ProductCatalog;
	}
	
	public void goToLoginPage() {
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}
	
	public String getErrorMesage() {
		waitUntilWebElementVisible(errormsg);
		return errormsg.getText();
	}
	
}
