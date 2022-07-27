package PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	
	WebDriver driver;
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement loginBtn;
	
	public LogInPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void enterEmail(String userName)
	{
		emailField.sendKeys(userName);
	}
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	public void clickLoginBtn()
	{
		loginBtn.click();
	}


}
