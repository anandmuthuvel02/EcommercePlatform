package stepdefenition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ecommerce.PageObjects.LoginPageObjects;
import ecommerce.PageObjects.MyCartPageObjects;
import ecommerce.PageObjects.OrderConfirmationPageObjects;
import ecommerce.PageObjects.PaymentPageObjects;
import ecommerce.PageObjects.ProductCatalogObjects;
import ecommerce.testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefenition extends BaseTest {
	
	public LoginPageObjects login;
	public ProductCatalogObjects ProductCatalog;
	public MyCartPageObjects CartPage;
	public PaymentPageObjects PaymentPage;
	public OrderConfirmationPageObjects  OrderConfirmation;
	
	@Given("user landed on ecommerce login page")
	public void user_landed_on_ecommerce_login_page() throws IOException {
	login =  launchToApplication();
	}
	
	@Given("^user logedin using Username (.+) and password (.+)$")
	public void user_logedin_using_Username_and_password(String UserName,String Password)
	{
		ProductCatalog =login.loginApplication(UserName, Password);
	}
	
	@When("^user add product (.+) to cart$")
	public void user_add_product_to_cart(String Productname)
	{
		List<WebElement> ProductsName =  ProductCatalog.getProducts();
		ProductCatalog.addToCartExpectedProduct(Productname);
		CartPage= ProductCatalog.goToCartPage();
	}
	
	@And ("^Checkout the (.+) and submit the order$")
	public void Checkout_the_and_submit_the_order(String Productname)
	{
		List<WebElement> CartProducts =CartPage.getCartProducts();
		Boolean Match = CartPage.VerifyExpectedProductSelected(CartProducts,Productname);
		Assert.assertTrue(Match);
		
		PaymentPage = CartPage.clickCheckout();
	}
	
	@Then("^user select country (.+)$")
	public void user_verify_Cofirmation_Message_displayed(String CountryName)
	{
		OrderConfirmation = PaymentPage.selectCountry(CountryName);
		
	}
	
	@And("verify {string} is displayed")
	public void verify_Confirm_is_displayed(String confrimmsg)
	{
		String ConfrimMsg = OrderConfirmation.getConfirmMsg();
		Assert.assertTrue(ConfrimMsg.equalsIgnoreCase(confrimmsg));
		closeWindow();
	}
	
	@Then("Verify {string} is displayed")
	public void user_verify_Error_Message_displayed(String errorMsg)
	{
		Assert.assertEquals(errorMsg,login.getErrorMesage()); 
		closeWindow();
	}
}
