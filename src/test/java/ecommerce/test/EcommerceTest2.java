package ecommerce.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import ecommerce.PageObjects.MyCartPageObjects;
import ecommerce.PageObjects.MyOrdersPage;
import ecommerce.PageObjects.OrderConfirmationPageObjects;
import ecommerce.PageObjects.PaymentPageObjects;
import ecommerce.PageObjects.ProductCatalogObjects;
import ecommerce.testComponents.BaseTest;

public class EcommerceTest2 extends BaseTest{
	//String  ExpProduct= "ADIDAS ORIGINAL";
	
	@Test (dataProvider= "getData", groups= {"DataProvider"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

		
		String CountryName = "Ind";

		ProductCatalogObjects ProductCatalog = login.loginApplication(input.get("Email"),input.get("Password"));
		List<WebElement> ProductsName =  ProductCatalog.getProducts();
		ProductCatalog.addToCartExpectedProduct(input.get("ExpProduct"));
		MyCartPageObjects CartPage= ProductCatalog.goToCartPage();

		
		List<WebElement> CartProducts =CartPage.getCartProducts();
		Boolean Match = CartPage.VerifyExpectedProductSelected(CartProducts,input.get("ExpProduct"));
		Assert.assertTrue(Match);
		
		PaymentPageObjects PaymentPage = CartPage.clickCheckout();
		OrderConfirmationPageObjects  OrderConfirmation = PaymentPage.selectCountry(CountryName);
		String ConfrimMsg = OrderConfirmation.getConfirmMsg();
		Assert.assertTrue(ConfrimMsg.equalsIgnoreCase("Thankyou for the order."));
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		String  ExpProduct= "ADIDAS ORIGINAL";
		ProductCatalogObjects ProductCatalog = login.loginApplication("anandmuthuvel02@gmail.com", "Asdf@2024");
		MyOrdersPage OrdersPage = ProductCatalog.goToOrdersPage();
		Assert.assertTrue(OrdersPage.VerifyProductReflectedInOrders(ExpProduct));
	}
	
	@DataProvider()
	public Object getData() throws IOException {
		
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"//src//test//java//ecommerce//testData//PurchseData.json");
		       
		return new Object [][]  {{data.get(0)},{data.get(1)},{data.get(2)}};
	}
	
	/*
	 * @DataProvider() public Object getData() {
	 * 
	 * HashMap <String,String > map = new HashMap <String,String >();
	 * map.put("Email","anandmuthuvel02@gmail.com");
	 * map.put("Password","Asdf@2024"); map.put("ExpProduct", "ADIDAS ORIGINAL");
	 * 
	 * 
	 * 
	 * HashMap <String,String > map2 = new HashMap <String,String >();
	 * map2.put("Email","sample02@gmail.com"); map2.put("Password", "Qwer@1234");
	 * map2.put("ExpProduct", "IPHONE 13 PRO");
	 * 
	 * 
	 * return new Object [][] {{map},{map2}}; }
	 */
	
	/*
	 * @DataProvider() public Object getData() { Object data [][] =
	 * {{"anandmuthuvel02@gmail.com","Asdf@2024","ADIDAS ORIGINAL"},{
	 * "sample02@gmail.com","Qwer@1234","IPHONE 13 PRO
"}}; return data; }
	 */
}
