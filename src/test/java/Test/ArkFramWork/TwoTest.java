package Test.ArkFramWork;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TwoTest {
	public WebDriver driver;
	BaseClass base;
	Logger log;
	@Test
	public void twoTest() throws IOException, InterruptedException {
		System.out.println("Changes done by other, now before start pullthe code");
		System.out.println("Changes done by interndevlopment , branching cocept");
		base = new BaseClass();
		driver = base.launchBrowser();
		// Log4J implementation
		log = LogManager.getLogger(LoginTest.class.getName());
		log.debug("Launch Browserr class ---->2");
		driver.get(base.propFun().getProperty("url"));
		log.debug("Open the URLr class ---->2");
		
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
		log.debug("Clossing the Browser");
	}
}
