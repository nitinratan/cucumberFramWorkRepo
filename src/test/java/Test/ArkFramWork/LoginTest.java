package Test.ArkFramWork;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

import pageObjects.AccountPage;
import pageObjects.LandingPage;

public class LoginTest {

	public WebDriver driver;
	BaseClass base;
	Logger log;

	@BeforeMethod
	public void openUrl() throws IOException, InterruptedException {
		base = new BaseClass();
		driver = base.launchBrowser();
		// Log4J implementation
		log = LogManager.getLogger(LoginTest.class.getName());
		log.debug("Launch Browser");
		driver.get(base.propFun().getProperty("url"));
		log.debug("Open the URL");
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedstatus) throws IOException, InterruptedException {

		LandingPage landingPageRef = new LandingPage(driver);
		landingPageRef.accountLink().click();
		landingPageRef.loginLink().click();
		// debug is a nornal log
		log.debug("Login Link Clicked");
		Thread.sleep(3000);
		LoginPage loginPageRef = new LoginPage(driver);
		loginPageRef.emailField().sendKeys(email);
		loginPageRef.passwordField().sendKeys(password);
		loginPageRef.loginButton().click();
		Thread.sleep(3000);
		String expectedResult = expectedstatus;
		AccountPage accountPageRef = new AccountPage(driver);
		// String actulaAccountHeading = accountPageRef.accouountSection().getText();
		String actualresult = null;
		try {
			accountPageRef.accouountSection().isDisplayed();
			actualresult = "Successfull";
			log.info("User loging with right credentials");

		} catch (Exception e) {
			System.out.println(expectedResult);
			actualresult = "faliure";
			Assert.assertTrue(false);
			log.info("User unable to login with wrong data");
		}

		Assert.assertEquals(actualresult, expectedResult);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		log.debug("Clossing the Browser");
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "veer@gmail.com", "12345", "Successfull" }, { "vejyar@gmail.com", "45676", "faliure" } };
		return data;
	}
}
