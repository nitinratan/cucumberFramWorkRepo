package Test.ArkFramWork;

import java.io.IOException;

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

	WebDriver driver;
	BaseClass base;

	@BeforeMethod
	public void openUrl() throws IOException, InterruptedException {
		base = new BaseClass();
		driver = base.launchBrowser();
		driver.get(base.propFun().getProperty("url"));
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedstatus) throws IOException, InterruptedException {

		LandingPage landingPageRef = new LandingPage(driver);
		landingPageRef.accountLink().click();
		landingPageRef.loginLink().click();
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

		} catch (Exception e) {
			System.out.println(expectedResult);
			actualresult = "faliure";
		}

		Assert.assertEquals(actualresult, expectedResult);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "veer@gmail.com", "12345", "Successfull" }, { "vejyar@gmail.com", "45676", "faliure" } };
		return data;
	}
}
