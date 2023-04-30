package Test.ArkFramWork;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LandingPage;

public class LoginTest {

	WebDriver driver;
	@BeforeMethod
	public void openUrl() throws IOException, InterruptedException {
		BaseClass base = new BaseClass();
		driver =   base.launchBrowser();
		driver.get(base.propFun().getProperty("url"));
		Thread.sleep(3000);
	}
	@Test
	public void login() throws IOException, InterruptedException {
		
		LandingPage landingPageRef = new LandingPage(driver);
		landingPageRef.accountLink().click();
		
		landingPageRef.loginLink().click();
		Thread.sleep(3000);
	}
	@AfterMethod
	public void browserCloser() {
		driver.close();
	}
}
