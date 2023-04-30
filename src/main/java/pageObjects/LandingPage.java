package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement accountLink;
	public WebElement accountLink() {
		return accountLink;
	}
	@FindBy(xpath ="//li[@class='dropdown open']//li[2]")
	WebElement loginLink;
	public WebElement loginLink() {
		return loginLink;
	}
}
