package pageObjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*WithOut Page FACTORY--------------------------------------------------------------------------------------------
	
	By emailField = By.id("input-email");
	public WebElement emailField() {
		return driver.findElement(emailField);
		
	}------------------------------------------------------------------------------------------------------------------*/
	
	@FindBy(id = "input-email")
	WebElement emailField;
	public WebElement emailField() {
		return emailField;
	}
}
