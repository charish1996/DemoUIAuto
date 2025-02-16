package org.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import utils.TestDataProvider;

public class LoginTest {
	
	WebDriver driver;
	
	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser) throws Exception{
		switch(browser.toLowerCase()) {
		case "chrome":
			driver  = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
			default:
				throw new Exception("incorrect browser");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void launchUrl() throws InterruptedException {
		driver.get("https://www.saucedemo.com/");
		String titletext = driver.getTitle();
		System.out.println(titletext);
	}
	@Test(dataProvider = "logindata",dataProviderClass = TestDataProvider.class)
	public void Login(String username,String password) throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[id='login-button']")).click();
		String appLogo = driver.findElement(By.cssSelector("[class='app_logo']")).getText();
		String expectedLogo = "Swag Labs";
		
		Assert.assertEquals(appLogo,expectedLogo,"Login failed");
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
