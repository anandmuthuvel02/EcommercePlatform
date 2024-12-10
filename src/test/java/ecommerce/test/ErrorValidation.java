package ecommerce.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import ecommerce.PageObjects.MyCartPageObjects;
import ecommerce.PageObjects.ProductCatalogObjects;
import ecommerce.testComponents.BaseTest;
import ecommerce.testComponents.Retry;

public class ErrorValidation extends BaseTest{
 
	@Test (groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() {
		
		login.loginApplication("anandmuthuvel03@gmial.com", "werty");
		Assert.assertEquals("Incorrect emailpassword.",login.getErrorMesage());
	}
	
	@Test (groups = {"ErrorHandling"})
	public void ProductValidation() {
		
		String ExpProduct = "ADIDAS ORIGINAL";
		ProductCatalogObjects ProductCatalog = login.loginApplication("sample02@gmail.com", "Qwer@1234");
		List<WebElement> ProductsName =  ProductCatalog.getProducts();
		ProductCatalog.addToCartExpectedProduct(ExpProduct);
		MyCartPageObjects CartPage= ProductCatalog.goToCartPage();

		
		List<WebElement> CartProducts =CartPage.getCartProducts();
		Boolean Match = CartPage.VerifyExpectedProductSelected(CartProducts,ExpProduct);
		Assert.assertTrue(Match);
	}
}
