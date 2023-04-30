package Test.ArkFramWork;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ThreeTest {
	public WebDriver driver;
	BaseClass base;
	Logger log;
	@Test
	public void threeTest() throws IOException, InterruptedException {
		base = new BaseClass();
		driver = base.launchBrowser();
		// Log4J implementation
		log = LogManager.getLogger(LoginTest.class.getName());
		log.debug("Launch Browser class ---->3");
		driver.get(base.propFun().getProperty("url"));
		Assert.assertTrue(false);
		log.debug("Open the URL class ---->3");
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
		log.debug("Clossing the Browser");
	}
	
}
