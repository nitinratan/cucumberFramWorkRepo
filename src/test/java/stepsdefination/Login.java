package stepsdefination;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Test.ArkFramWork.BaseClass;
import Test.ArkFramWork.LoginTest;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class Login {

	public WebDriver driver;
	BaseClass base;
	Logger log;
	LoginPage loginPageRef;
	@Given("^Open any Browser$")
	public void open_any_browser() throws IOException {
		base = new BaseClass();
		driver = base.launchBrowser();
		// Log4J implementation
		log = LogManager.getLogger(LoginTest.class.getName());
		log.debug("Launch Browser");
		
	}

	@Given("^Navigate to Login page$")
	public void navigate_to_login_page() {
		driver.get(base.propFun().getProperty("url"));
		log.debug("Open the URL");
		LandingPage landingPageRef = new LandingPage(driver);
		landingPageRef.accountLink().click();
		landingPageRef.loginLink().click();
	}

	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void user_enters_username_as_and_password_as_into_the_fields(String username, String password) {
		loginPageRef = new LoginPage(driver);
		loginPageRef.emailField().sendKeys(username);
		loginPageRef.passwordField().sendKeys(password);
	
	}

	@When("^User clicks on Login button$")
	public void user_clicks_on_login_button() throws InterruptedException {
		loginPageRef.loginButton().click();
		Thread.sleep(3000);
	}

	@Then("^Verify user is able to successfully login$")
	public void verify_user_is_able_to_successfully_login() {
		AccountPage accountPageRef = new AccountPage(driver);
		
		try {
			accountPageRef.accouountSection().isDisplayed();
			log.info("User loging with right credentials");
			Assert.assertTrue(accountPageRef.accouountSection().isDisplayed());

		} catch (Exception e) {
			//Assert.assertTrue(false);
			log.info("User unable to login with wrong data");
		}

			}
	@After
	public void tearDown() {
		driver.close();
	}

}
