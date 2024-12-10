package ecommerce.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerce.PageObjects.LoginPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPageObjects login;

	public WebDriver initializerDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ecommerce//resources//GlobalData.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser") !=null?System.getProperty("browser"): prop.getProperty("browser");;
	//	String browserName = prop.getProperty("browser");

		if(browserName.contains("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("Headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));

		}
		else if(browserName.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver","/Users/Admin/Documents/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		}
		else if(browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver","/Users/Admin/Documents/Drivers/msedgedriver.exe");
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {

		Path path = Paths.get(filePath);
		String JsonContent = Files.readString(path);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent,new TypeReference<List<HashMap<String,String>>>(){});

		return data;
	}
	
	public String getScreenShot(String TestcaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Src,new File(System.getProperty("user.dir")+File.separator+"reports"+File.separator+TestcaseName+".png"));
		return System.getProperty("user.dir")+File.separator+"reports"+File.separator+TestcaseName+".png";
	}

	@BeforeMethod(alwaysRun=true)
	public LoginPageObjects launchToApplication() throws IOException {
		driver = initializerDriver();	
		login = new LoginPageObjects(driver);
		login.goToLoginPage();
		return login;
	}

	@AfterMethod(alwaysRun=true)
	public void closeWindow() {
		driver.close();
	}
}
