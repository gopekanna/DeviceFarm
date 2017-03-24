package test.appium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {
AndroidDriver<WebElement> driver;
	
	String emailId = "sakthivel@testleaf.com";
	String defPassword = "XXXXXXXX";
	String company = "TestLeaf";

	@BeforeMethod
	 public void setUp() throws MalformedURLException {
		// TODO Auto-generated method stub
	
		File targetApp = new File("/Users/gopikannan/Downloads/LeafOrg.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "RQ30011RJZ");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("autoWebview", "true");
//		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("app", targetApp.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.testleaf.leaforg");
		capabilities.setCapability("appActivity", "com.testleaf.leaforg.MainActivity");
		
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		}
	

	 @Test(priority = 1)
	 public void SignIn() throws InterruptedException, IOException  {
		
		 WebDriverWait wait= new WebDriverWait(driver, 60);
			Thread.sleep(3000);

			Set<String> contextNames = driver.getContextHandles();

			for (String contextName : contextNames) {

				if (contextName.contains("WEBVIEW"))
					driver.context(contextName);				
			}

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Create an Account')]")));
			driver.findElementByXPath("(//input[@formcontrolname='email'])").sendKeys(emailId);
			driver.findElementByXPath("(//input[@formcontrolname='password'])").sendKeys(defPassword);
			driver.findElementByXPath("//span[contains(text(),'Login')]").click();

			Thread.sleep(3000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//Span[contains(text(),'Settings')]")));
			driver.findElementByXPath("//Span[contains(text(),'Settings')]").click();
			driver.findElementByXPath("(//ion-icon[@aria-label='arrow forward'])[2]").click();
			Thread.sleep(3000);
	}
	 
	 @AfterMethod
	 public void tearDown()
	 {
		 driver.quit();
	 }

}
